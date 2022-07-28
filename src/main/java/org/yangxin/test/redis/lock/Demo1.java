package org.yangxin.test.redis.lock;

import redis.clients.jedis.Jedis;

/**
 * @author yangxin
 * 2022/7/28 21:19
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread", "AlibabaUndefineMagicConstant"})
public class Demo1 {

    public static final String LOCK_NAME = "LOCK";

    public static final String LOCK_VALUE = "ERICK";

    public static final int EXPIRE_SECS = 5;

    private static Jedis getJedis() {
        return new Jedis("192.168.1.104", 6379);
    }

    public static void main(String[] args) {
        new Thread(Demo1::secondLock).start();
        new Thread(Demo1::secondLock).start();
    }

    /**
     * 场景一：假如释放锁失败，则后面永远无法执行
     */
    private static void firstLock() {
        // 1 上锁
        Jedis redis = getJedis();
        Long lockResult = redis.setnx(LOCK_NAME, LOCK_VALUE);
        if (lockResult == 1) {
            // 2 执行业务
            executeBusiness();
            // 3 释放锁
            redis.del(LOCK_NAME);
        } else {
            // 获取锁失败
            System.out.println("Can not get lock.");
        }
    }

    /**
     * 场景二：释放锁失败，通过自动过期来保证
     */
    private static void secondLock() {
        Jedis redis = getJedis();
        String lockResult = redis.set(LOCK_NAME, LOCK_VALUE, "NX", "EX", EXPIRE_SECS);
        if ("OK".equalsIgnoreCase(lockResult)) {
            executeBusiness();
            redis.del(LOCK_NAME);
        } else {
            System.out.println("Can not get lock.");
        }
    }

    private static void executeBusiness() {
        System.out.println("Business execution......");
    }
}
