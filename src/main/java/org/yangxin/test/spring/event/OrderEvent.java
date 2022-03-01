package org.yangxin.test.spring.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 事件
 *
 * @author yangxin
 * 2022/3/1 9:17
 */
@Setter
@Getter
public class OrderEvent extends ApplicationEvent {

    private static final long serialVersionUID = -7777474748329488017L;

    private Object object;

    public OrderEvent(Object source, Object o) {
        super(source);
        this.object = o;
    }
}
