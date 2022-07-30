package org.yangxin.test.redis.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2022/7/30 11:33
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class Demo2 {

    private static final String LOCK_KEY = "COMMERCE-BUSINESS";

    /**
     * @return Redisson的配置类
     */
    private static RedissonClient redissonClient() {
        Config config = new Config();
        // redis 单节点
        config.useSingleServer().setAddress("redis://192.168.1.104:6379");

        return Redisson.create(config);
    }

    private static void executeBusiness() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Business executing......");
    }

    /**
     * 基本使用
     * 可以使用联锁来解决主从一致性问题（设立多个redis作为主节点，只有每个都获取成功的时候，才会去执行）
     */
    private static void lockMethod() {
        RedissonClient redissonClient = redissonClient();

        // RLock extends Lock
        RLock lock = redissonClient.getLock(LOCK_KEY);
        // 可重入锁：默认超时时间为30s
        if (lock.tryLock()) {
            try {
                executeBusiness();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("Lock released");
            }
        } else {
            System.out.println("Can not get lock");
        }
    }

    /**
     * 等待超时的锁
     */
    private static void lockMethodWithRetry() {
        RedissonClient redissonClient = redissonClient();
        // 获取对应的key的锁
        RLock lock = redissonClient.getLock(LOCK_KEY);

        /*
            内部包含重试机制，通过Redis的发布订阅者模式来实现
            参数一：最长等待时间，超时则不再等待
            参数二：锁超时释放时间
            参数三：时间单位
         */
        boolean hasLock = false;
        try {
            hasLock = lock.tryLock(6, 20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (hasLock) {
            try {
                executeBusiness();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("Lock released");
            }
        } else {
            System.out.println("Can not get lock");
        }
    }

    public static void main(String[] args) {
        new Thread(Demo2::lockMethodWithRetry).start();
        new Thread(Demo2::lockMethodWithRetry).start();
    }
}
