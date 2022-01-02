package org.yangxin.test.disruptor.quickstart;

import com.lmax.disruptor.EventHandler;

/**
 * @author yangxin
 * 2022/1/2 19:07
 */
public class OrderEventHandler implements EventHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者：" + event.getValue());
    }
}
