package org.yangxin.diy.redission;

import lombok.Data;

/**
 * @author yangxin
 * 2023/2/16 12:00
 */
@Data
public class Redisson implements RedissonClient {

    private final Config config;

    public Redisson(Config config) {
        this.config = config;
    }

    public static RedissonClient create(Config config) {
        return new Redisson(config);
    }

    @Override
    public RLock getLock(String name) {
        return new RedissonLock();
    }
}
