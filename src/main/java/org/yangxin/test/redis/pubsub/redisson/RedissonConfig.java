package org.yangxin.test.redis.pubsub.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangxin
 * 2023/8/6 18:14
 */
@Configuration
@Slf4j
public class RedissonConfig {

    @Bean(name = "redissonClient")
    public RedissonClient getRedisConfig() {
        Config config = new Config();
        // 单机
        config.useSingleServer().setAddress("redis://192.168.1.102:6379");
        return Redisson.create(config);
    }
}
