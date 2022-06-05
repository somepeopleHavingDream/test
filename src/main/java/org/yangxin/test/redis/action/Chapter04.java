package org.yangxin.test.redis.action;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * set inventory:seller -> 用户包裹里每件商品的唯一编号
 * zset market: -> 该卖家此商品的售价
 * hash users:userId -> 每个用户的具体信息
 *
 * @author yangxin
 * 2022/6/5 16:31
 */
@SuppressWarnings("AlibabaAvoidDoubleOrFloatEqualCompare")
public class Chapter04 {

    public static void main(String[] args) {
        new Chapter04().run();
    }

    public void run() {
        Jedis conn = new Jedis(ConfigConstant.HOST, ConfigConstant.PORT);
        conn.auth(ConfigConstant.PASSWORD);
        conn.select(15);

        testListItem(conn, false);
        testPurchaseItem(conn);
        testBenchmarkUpdateToken(conn);
    }

    public void testBenchmarkUpdateToken(Jedis conn) {
        System.out.println("\n----- testBenchmarkUpdate -----");
        benchmarkUpdateToken(conn, 5);
    }

    public void benchmarkUpdateToken(Jedis conn, int duration) {
        try {
            @SuppressWarnings("rawtypes")
            Class[] args = new Class[]{
                    Jedis.class, String.class, String.class, String.class};
            Method[] methods = new Method[]{
                    this.getClass().getDeclaredMethod("updateToken", args),
                    this.getClass().getDeclaredMethod("updateTokenPipeline", args),
            };
            // 测试会分别执行update_token()函数和update_token_pipeline()函数
            for (Method method : methods) {
                // 设置计数器以及测试结束的条件
                int count = 0;
                long start = System.currentTimeMillis();
                long end = start + (duration * 1000L);
                while (System.currentTimeMillis() < end) {
                    count++;
                    // 调用两个函数的其中一个
                    method.invoke(this, conn, "token", "user", "item");
                }
                // 计算函数的执行时长
                long delta = System.currentTimeMillis() - start;
                // 打印测试结果
                System.out.println(
                        method.getName() + ' ' +
                                count + ' ' +
                                (delta / 1000) + ' ' +
                                (count / (delta / 1000)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateToken(Jedis conn, String token, String user, String item) {
        long timestamp = System.currentTimeMillis() / 1000;
        conn.hset("login:", token, user);
        conn.zadd("recent:", timestamp, token);
        if (item != null) {
            conn.zadd("viewed:" + token, timestamp, item);
            conn.zremrangeByRank("viewed:" + token, 0, -26);
            conn.zincrby("viewed:", -1, item);
        }
    }

    public void updateTokenPipeline(Jedis conn, String token, String user, String item) {
        long timestamp = System.currentTimeMillis() / 1000;
        Pipeline pipe = conn.pipelined();
        pipe.multi();
        pipe.hset("login:", token, user);
        pipe.zadd("recent:", timestamp, token);
        if (item != null){
            pipe.zadd("viewed:" + token, timestamp, item);
            pipe.zremrangeByRank("viewed:" + token, 0, -26);
            pipe.zincrby("viewed:", -1, item);
        }
        pipe.exec();
    }

    public void testPurchaseItem(Jedis conn) {
        System.out.println("\n----- testPurchaseItem -----");
        testListItem(conn, true);

        System.out.println("We need to set up just enough state so a user can buy an item");
        conn.hset("users:userY", "funds", "125");
        Map<String, String> r = conn.hgetAll("users:userY");
        System.out.println("The user has some money:");
        for (Map.Entry<String, String> entry : r.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        assert r.size() > 0;
        assert r.get("funds") != null;
        System.out.println();

        System.out.println("Let's purchase an item");
        boolean p = purchaseItem(conn, "userY", "itemX", "userX", 10);
        System.out.println("Purchasing an item succeeded? " + p);
        assert p;
        r = conn.hgetAll("users:userY");
        System.out.println("Their money is now:");
        for (Map.Entry<String, String> entry : r.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        assert r.size() > 0;

        String buyer = "userY";
        Set<String> i = conn.smembers("inventory:" + buyer);
        System.out.println("Their inventory is now:");
        for (String member : i) {
            System.out.println("  " + member);
        }
        assert i.size() > 0;
        assert i.contains("itemX");
        assert conn.zscore("market:", "itemX.userX") == null;
    }

    public boolean purchaseItem(
            Jedis conn, String buyerId, String itemId, String sellerId, double lprice) {

        String buyer = "users:" + buyerId;
        String seller = "users:" + sellerId;
        String item = itemId + '.' + sellerId;
        String inventory = "inventory:" + buyerId;
        long end = System.currentTimeMillis() + 10000;

        // 当买家的个人信息或者商品买卖市场出现变化而导致WatchError异常出现时，程序将进行重试，其中最大重试时间为10秒
        while (System.currentTimeMillis() < end) {
            // 对商品买卖市场以及买家的个人信息进行监视
            conn.watch("market:", buyer);

            // 检查买家想要购买的商品价格是否出现了变化，以及买家是否有足够的钱来购买这件商品
            double price = conn.zscore("market:", item);
            double funds = Double.parseDouble(conn.hget(buyer, "funds"));
            if (price != lprice || price > funds) {
                conn.unwatch();
                return false;
            }

            // 先将买家支付的钱转移给卖家，然后将被购买的商品移交给买家
            Transaction trans = conn.multi();
            trans.hincrBy(seller, "funds", (int) price);
            trans.hincrBy(buyer, "funds", (int) -price);
            trans.sadd(inventory, itemId);
            trans.zrem("market:", item);
            List<Object> results = trans.exec();
            // null response indicates that the transaction was aborted due to
            // the watched key changing.
            if (results == null) {
                // 如果买家的个人信息或者商品买卖市场在交易的过程中出现了变化，那么进行重试
                continue;
            }
            return true;
        }

        return false;
    }

    public void testListItem(Jedis conn, boolean nested) {
        if (!nested) {
            System.out.println("\n----- testListItem -----");
        }

        System.out.println("We need to set up just enough state so that a user can list an item");
        String seller = "userX";
        String item = "itemX";
        conn.sadd("inventory:" + seller, item);
        Set<String> i = conn.smembers("inventory:" + seller);

        System.out.println("The user's inventory has:");
        for (String member : i) {
            System.out.println("  " + member);
        }
        assert i.size() > 0;
        System.out.println();

        System.out.println("Listing the item...");
        boolean l = listItem(conn, item, seller, 10);
        System.out.println("Listing the item succeeded? " + l);
        assert l;
        Set<Tuple> r = conn.zrangeWithScores("market:", 0, -1);
        System.out.println("The market contains:");
        for (Tuple tuple : r) {
            System.out.println("  " + tuple.getElement() + ", " + tuple.getScore());
        }
        assert r.size() > 0;
    }

    public boolean listItem(
            Jedis conn, String itemId, String sellerId, double price) {

        String inventory = "inventory:" + sellerId;
        String item = itemId + '.' + sellerId;
        long end = System.currentTimeMillis() + 5000;

        while (System.currentTimeMillis() < end) {
            // 监视用户包裹发生的变化
            conn.watch(inventory);
            // 检查用户是否仍然持有将要被销售的商品
            if (!conn.sismember(inventory, itemId)) {
                // 如果指定的商品不在用户的包裹里面，那么停止对包裹键的监视并返回一个空值
                conn.unwatch();
                return false;
            }

            // 把被销售的商品添加到商品买卖市场里面
            Transaction trans = conn.multi();
            trans.zadd("market:", price, item);
            trans.srem(inventory, itemId);

            // 如果执行execute方法没有引发WatchError异常，那么说明事务执行成功，并且对包裹键的监视也已经结束
            List<Object> results = trans.exec();
            // null response indicates that the transaction was aborted due to
            // the watched key changing.
            if (results == null) {
                // 用户的包裹已经发生了变化，重试
                continue;
            }
            return true;
        }
        return false;
    }
}
