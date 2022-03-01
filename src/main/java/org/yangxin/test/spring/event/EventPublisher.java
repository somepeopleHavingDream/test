package org.yangxin.test.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author yangxin
 * 2022/3/1 9:21
 */
@Component
public class EventPublisher {

    private final ApplicationContext context;

    @Autowired
    public EventPublisher(ApplicationContext context) {
        this.context = context;
    }

    public void publish(Object o) {
        context.publishEvent(new OrderEvent(this, o));
    }
}
