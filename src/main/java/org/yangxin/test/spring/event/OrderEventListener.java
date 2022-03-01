package org.yangxin.test.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 事件监听
 *
 * @author yangxin
 * 2022/3/1 9:19
 */
@SuppressWarnings("NullableProblems")
@Component
public class OrderEventListener implements ApplicationListener<OrderEvent> {

    @Async
    @Override
    public void onApplicationEvent(OrderEvent event) {
        System.out.println(Thread.currentThread().getName() + "开始工作");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String s = event.getObject().toString();
        System.out.println("结束工作" + s);
    }
}
