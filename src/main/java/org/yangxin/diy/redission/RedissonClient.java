package org.yangxin.diy.redission;

/**
 * @author yangxin
 * 2023/2/18 1:50
 */
public interface RedissonClient {

    RLock getLock(String lockKey);
}
