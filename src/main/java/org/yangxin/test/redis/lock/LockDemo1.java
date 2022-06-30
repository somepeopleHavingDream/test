package org.yangxin.test.redis.lock;

import redis.clients.jedis.Jedis;

/**
 * @author yangxin
 * 2022/6/28 22:58
 */
public class LockDemo1 {

    public static final String LOCK_NAME = "LOCK";

    public static final String LOCK_VALUE = "ERICK";

    public static final int EXPIRE_SECS = 5;

    private static Jedis getJedis() {
        return new Jedis("60.205.229.31", 6381);
    }

    public static void main(String[] args) {
    }
}
