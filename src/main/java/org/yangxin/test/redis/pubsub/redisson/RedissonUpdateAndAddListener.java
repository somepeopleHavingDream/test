package org.yangxin.test.redis.pubsub.redisson;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author yangxin
 * 2023/8/6 15:23
 */
@Getter
@Component
@Slf4j
public class RedissonUpdateAndAddListener implements ApplicationRunner {

    /**
     * 监听的主题
     */
    private final String topic = "__keyevent@*__:set";

    private final RedissonClient redissonClient;

    @Autowired
    public RedissonUpdateAndAddListener(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        redissonClient.getPatternTopic(topic, StringCodec.INSTANCE)
                .addListener(String.class, (c1, c2, s)
                        -> log.info("redissonUpdateAndAddListener c1->{}, c2->{}, s->{}", c1, c2, s));

    }
}
