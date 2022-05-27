package org.yangxin.test.redis.action;

import redis.clients.jedis.Jedis;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * hash login: -> 每个token对应了哪个用户
 * zset recent: -> 每个token的最近时间
 * zset viewd:token -> 该用户浏览某件商品的最近时间点
 * zset viewd: -> 每件商品的个数
 * hash cart:session -> 记录该用户购物车中每件商品的数量
 * zset delay: -> 每一行的时延
 * zset schedule: -> 每一行的时间
 * string inv:rowId -> 商品的具体信息
 * string cache:requestHashcode -> 页面内容
 *
 * @author yangxin
 * 2022/5/26 17:30
 */
@SuppressWarnings({"SpellCheckingInspection", "AlibabaUndefineMagicConstant"})
public class Chapter02 {

    public static void main(String[] args) throws InterruptedException {
        new Chapter02().run();
    }

    public void run()
            throws InterruptedException {
        Jedis conn = new Jedis(ConfigConstant.HOST, ConfigConstant.PORT);
        conn.auth(ConfigConstant.PASSWORD);
        conn.select(15);

        testLoginCookies(conn);
        testShopppingCartCookies(conn);
        testCacheRequest(conn);
    }

    public void testCacheRequest(Jedis conn) {
        System.out.println("\n----- testCacheRequest -----");
        String token = UUID.randomUUID().toString();

        Callback callback = request -> "content for " + request;

        updateToken(conn, token, "username", "itemX");
        String url = "http://test.com/?item=itemX";
        System.out.println("We are going to cache a simple request against " + url);
        String result = cacheRequest(conn, url, callback);
        System.out.println("We got initial content:\n" + result);
        System.out.println();

        assert result != null;

        System.out.println("To test that we've cached the request, we'll pass a bad callback");
        String result2 = cacheRequest(conn, url, null);
        System.out.println("We ended up getting the same response!\n" + result2);

        assert result.equals(result2);

        assert !canCache(conn, "http://test.com/");
        assert !canCache(conn, "http://test.com/?item=itemX&_=1234536");
    }

    public String cacheRequest(Jedis conn, String request, Callback callback) {
        // 对于不能被缓存的请求，直接调用回调函数
        if (!canCache(conn, request)) {
            return callback != null ? callback.call(request) : null;
        }

        // 将请求转换成一个简单的字符串键，方便之后进行查找
        String pageKey = "cache:" + hashRequest(request);
        String content = conn.get(pageKey);

        if (content == null && callback != null) {
            // 如果页面还没有被缓存，那么生成页面
            content = callback.call(request);
            // 将新生成的页面放到缓存里
            conn.setex(pageKey, 300, content);
        }

        // 返回页面
        return content;
    }

    public boolean canCache(Jedis conn, String request) {
        try {
            URL url = new URL(request);
            HashMap<String, String> params = new LinkedHashMap<>();
            if (url.getQuery() != null) {
                for (String param : url.getQuery().split("&")) {
                    String[] pair = param.split("=", 2);
                    params.put(pair[0], pair.length == 2 ? pair[1] : null);
                }
            }

            String itemId = extractItemId(params);
            if (itemId == null || isDynamic(params)) {
                return false;
            }
            Long rank = conn.zrank("viewed:", itemId);
            return rank != null && rank < 10000;
        } catch (MalformedURLException mue) {
            return false;
        }
    }

    public boolean isDynamic(Map<String, String> params) {
        return params.containsKey("_");
    }

    public String extractItemId(Map<String, String> params) {
        return params.get("item");
    }

    public String hashRequest(String request) {
        return String.valueOf(request.hashCode());
    }

    public void testShopppingCartCookies(Jedis conn)
            throws InterruptedException {
        System.out.println("\n----- testShopppingCartCookies -----");
        String token = UUID.randomUUID().toString();

        System.out.println("We'll refresh our session...");
        updateToken(conn, token, "username", "itemX");
        System.out.println("And add an item to the shopping cart");
        addToCart(conn, token, "itemY", 3);
        Map<String, String> r = conn.hgetAll("cart:" + token);
        System.out.println("Our shopping cart currently has:");
        for (Map.Entry<String, String> entry : r.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();

        assert r.size() >= 1;

        System.out.println("Let's clean out our sessions and carts");
        CleanFullSessionsThread thread = new CleanFullSessionsThread(0);
        thread.start();
        Thread.sleep(1000);
        thread.quit();
        Thread.sleep(2000);
        if (thread.isAlive()) {
            throw new RuntimeException("The clean sessions thread is still alive?!?");
        }

        r = conn.hgetAll("cart:" + token);
        System.out.println("Our shopping cart now contains:");
        for (Map.Entry<String, String> entry : r.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        assert r.size() == 0;
    }

    public void addToCart(Jedis conn, String session, String item, int count) {
        if (count <= 0) {
            // 从购物车里面移除指定的商品
            conn.hdel("cart:" + session, item);
        } else {
            // 将指定的商品添加到购物车
            conn.hset("cart:" + session, item, String.valueOf(count));
        }
    }

    public void testLoginCookies(Jedis conn)
            throws InterruptedException {
        System.out.println("\n----- testLoginCookies -----");
        String token = UUID.randomUUID().toString();

        updateToken(conn, token, "username", "itemX");
        System.out.println("We just logged-in/updated token: " + token);
        System.out.println("For user: 'username'");
        System.out.println();

        System.out.println("What username do we get when we look-up that token?");
        String r = checkToken(conn, token);
        System.out.println(r);
        System.out.println();
        assert r != null;

        System.out.println("Let's drop the maximum number of cookies to 0 to clean them out");
        System.out.println("We will start a thread to do the cleaning, while we stop it later");

        CleanSessionsThread thread = new CleanSessionsThread(0);
        thread.start();
        Thread.sleep(1000);
        thread.quit();
        Thread.sleep(2000);
        if (thread.isAlive()) {
            throw new RuntimeException("The clean sessions thread is still alive?!?");
        }

        long s = conn.hlen("login:");
        System.out.println("The current number of sessions still available is: " + s);
        assert s == 0;
    }

    public void updateToken(Jedis conn, String token, String user, String item) {
        // 获取当前时间戳
        long timestamp = System.currentTimeMillis() / 1000;
        // 维持令牌与已登录用户之间的映射
        conn.hset("login:", token, user);
        // 记录令牌最后一次出现的时间
        conn.zadd("recent:", timestamp, token);
        if (item != null) {
            // 记录用户浏览过的商品
            conn.zadd("viewed:" + token, timestamp, item);
            // 移除旧的记录，只保留用户最近浏览的25个商品
            conn.zremrangeByRank("viewed:" + token, 0, -26);
            conn.zincrby("viewed:", -1, item);
        }
    }

    public String checkToken(Jedis conn, String token) {
        // 尝试获取并返回令牌对应的用户
        return conn.hget("login:", token);
    }

    @SuppressWarnings({"BusyWait", "DuplicatedCode"})
    public static class CleanSessionsThread
            extends Thread {
        private final Jedis conn;
        private final int limit;
        private boolean quit;

        public CleanSessionsThread(int limit) {
            this.conn = new Jedis(ConfigConstant.HOST, ConfigConstant.PORT);
            conn.auth(ConfigConstant.PASSWORD);
            conn.select(15);
            this.limit = limit;
        }

        public void quit() {
            quit = true;
        }

        @Override
        public void run() {
            while (!quit) {
                // 找出目前已有令牌的数量
                long size = conn.zcard("recent:");
                // 令牌数量未超过限制，休眠并在之后重新检查
                if (size <= limit) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    continue;
                }

                // 获得需要移除的令牌Id
                long endIndex = Math.min(size - limit, 100);
                Set<String> tokenSet = conn.zrange("recent:", 0, endIndex - 1);
                String[] tokens = tokenSet.toArray(new String[0]);

                // 为那些将要被删除的令牌构建键名
                ArrayList<String> sessionKeys = new ArrayList<>();
                for (String token : tokens) {
                    sessionKeys.add("viewed:" + token);
                }

                // 移除最旧的那些令牌
                conn.del(sessionKeys.toArray(new String[0]));
                conn.hdel("login:", tokens);
                conn.zrem("recent:", tokens);
            }
        }
    }

    @SuppressWarnings({"DuplicatedCode", "BusyWait"})
    public static class CleanFullSessionsThread
            extends Thread {
        private final Jedis conn;
        private final int limit;
        private boolean quit;

        public CleanFullSessionsThread(int limit) {
            this.conn = new Jedis(ConfigConstant.HOST, ConfigConstant.PORT);
            conn.auth(ConfigConstant.PASSWORD);
            conn.select(15);
            this.limit = limit;
        }

        public void quit() {
            quit = true;
        }

        @Override
        public void run() {
            while (!quit) {
                long size = conn.zcard("recent:");
                if (size <= limit) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    continue;
                }

                long endIndex = Math.min(size - limit, 100);
                Set<String> sessionSet = conn.zrange("recent:", 0, endIndex - 1);
                String[] sessions = sessionSet.toArray(new String[0]);

                ArrayList<String> sessionKeys = new ArrayList<>();
                for (String sess : sessions) {
                    sessionKeys.add("viewed:" + sess);
                    // 新增加的这行代码用于删除旧会话对应用户的购物车
                    sessionKeys.add("cart:" + sess);
                }

                conn.del(sessionKeys.toArray(new String[0]));
                conn.hdel("login:", sessions);
                conn.zrem("recent:", sessions);
            }
        }
    }

    public interface Callback {
        String call(String request);
    }
}
