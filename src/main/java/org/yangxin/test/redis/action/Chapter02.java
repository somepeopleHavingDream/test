package org.yangxin.test.redis.action;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 * hash login: -> 每个token对应了哪个用户
 * zset recent: -> 每个token的最近时间
 * zset viewd:token -> 该用户浏览某件商品的最近时间点
 * zset viewd: -> 每件商品的个数
 * hash cart:session -> 记录该用户购物车中每件商品的数量
 * zset delay: -> 每一行的时延
 * zset schedule: -> 每一行的时间
 * string inv:rowId -> 商品的具体信息
 *
 * @author yangxin
 * 2022/5/26 17:30
 */
public class Chapter02 {

    public static void main(String[] args) throws InterruptedException {
        new Chapter02().run();
    }

    public void run()
            throws InterruptedException
    {
        Jedis conn = new Jedis(ConfigConstant.HOST, ConfigConstant.PORT);
        conn.auth(ConfigConstant.PASSWORD);
        conn.select(15);

        testLoginCookies(conn);
    }

    public void testLoginCookies(Jedis conn)
            throws InterruptedException
    {
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
        if (thread.isAlive()){
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

    @SuppressWarnings("BusyWait")
    public static class CleanSessionsThread
            extends Thread
    {
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
                if (size <= limit){
                    try {
                        sleep(1000);
                    }catch(InterruptedException ie){
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
}
