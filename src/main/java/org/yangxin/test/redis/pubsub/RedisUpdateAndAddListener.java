package org.yangxin.test.redis.pubsub;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.stereotype.Component;

/**
 * @author yangxin
 * 2023/8/6 15:23
 */
@Getter
@Component
@Slf4j
public class RedisUpdateAndAddListener implements MessageListener {

    /**
     * 监听的主题
     */
    private final PatternTopic topic = new PatternTopic("__keyevent@*__:set");

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String topic = new String(pattern);
        String msg = new String(message.getBody());
        log.info("updateAndAdd onMessage topic->{}, msg->{}", topic, msg);
    }
}
