package org.yangxin.test.redis.seckill;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 此类有问题，还是会出现超卖现象，需再调研一波redis的相关用法
 *
 * @author yangxin
 * 2021/11/13 下午11:22
 */
@SuppressWarnings({"AlibabaThreadShouldSetName", "AlibabaUndefineMagicConstant"})
public class SecKill {

    public static ThreadPoolExecutor pool = new ThreadPoolExecutor(10,
            100, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    /**
     * ip
     */
    private static final String IP = "localhost";

    /**
     * 端口
     */
    private static final Integer PORT = 6379;

    /**
     * 密码
     */
    private static final String AUTH = "123456";

    /**
     * 被选择的索引
     */
    private static final Integer SELECTED_INDEX = 0;

    /**
     * redis键-库存量
     */
    private static final String KEY_KILL_NUM = "kill:num";

    /**
     * redis键-成功秒杀的用户Id列表
     */
    private static final String KEY_KILL_USER = "kill:user";

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Jedis jedis = new Jedis(IP, PORT);
        jedis.auth(AUTH);
        jedis.select(SELECTED_INDEX);
        jedis.set(KEY_KILL_NUM, "50");
        jedis.del(KEY_KILL_USER);
        jedis.close();

        for (int i = 0; i < 1000; i++) {
            pool.execute(new KillTask());
        }
    }

    private static class KillTask implements Runnable {

        @Override
        public void run() {
            Jedis jedis = new Jedis(IP, PORT);
            jedis.auth(AUTH);
            jedis.select(SELECTED_INDEX);

            int num = Integer.parseInt(jedis.get(KEY_KILL_NUM));
            jedis.watch(KEY_KILL_NUM, KEY_KILL_USER);
            if (num > 0) {
                Transaction multi = jedis.multi();
                multi.decr(KEY_KILL_NUM);
                multi.rpush(KEY_KILL_USER, "9527");
                multi.exec();
            } else {
                pool.shutdown();
            }

            jedis.close();
        }
    }
}
