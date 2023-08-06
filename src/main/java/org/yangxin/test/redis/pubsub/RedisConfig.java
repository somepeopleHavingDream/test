package org.yangxin.test.redis.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author yangxin
 * 2023/8/6 15:11
 */
@Configuration
public class RedisConfig {

    private final RedisUpdateAndAddListener redisUpdateAndAddListener;

    @Autowired
    public RedisConfig(RedisUpdateAndAddListener redisUpdateAndAddListener) {
        this.redisUpdateAndAddListener = redisUpdateAndAddListener;
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        // 监听所有key的set事件
        container.addMessageListener(redisUpdateAndAddListener, redisUpdateAndAddListener.getTopic());

        return container;
    }
}
