package org.yangxin.test.redis.lock;

import redis.clients.jedis.Jedis;

import java.util.Objects;
import java.util.UUID;

/**
 * @author yangxin
 * 2022/7/28 21:19
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread", "AlibabaUndefineMagicConstant", "AlibabaRemoveCommentedCode", "SameParameterValue"})
public class Demo1 {

    public static final String LOCK_NAME = "LOCK";

    public static final String LOCK_VALUE = "ERICK";

    public static final int EXPIRE_SECS = 5;

    private static Jedis getJedis() {
        return new Jedis("192.168.1.104", 6379);
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
     * 场景二：释放锁失败，通过自动过期来保证，但是有可能存在勿删锁的情况
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

    private static void thirdLock(String lockKey, String lockValue) {
        Jedis redis = getJedis();
        String lockResult = redis.set(lockKey, lockValue, "NX", "EX", EXPIRE_SECS);
        if ("OK".equalsIgnoreCase(lockResult)) {
            executeBusiness();

            // 判断是否是自己的锁，是自己的再删除
            String presentValue = redis.get(lockKey);
            if (Objects.equals(lockValue, presentValue)) {
                redis.del(lockKey);
                System.out.println("lock deleted");
            } else {
                System.out.println("Can not get lock");
            }
        }
    }

    private static void executeBusiness() {
        System.out.println("Business execution......");
    }

    private static String getLockValue() {
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
//        new Thread(Demo1::secondLock).start();
//        new Thread(Demo1::secondLock).start();

        new Thread(() -> thirdLock(LOCK_NAME, getLockValue())).start();
    }
}
