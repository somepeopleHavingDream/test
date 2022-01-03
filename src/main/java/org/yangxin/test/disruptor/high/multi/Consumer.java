package org.yangxin.test.disruptor.high.multi;

import com.lmax.disruptor.WorkHandler;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yangxin
 * 2022/1/3 15:43
 */
public class Consumer implements WorkHandler<Order> {

    private final String consumerId;

    private static final AtomicInteger COUNT = new AtomicInteger(0);

    private final Random random = new Random();

    public Consumer(String consumerId) {
        this.consumerId = consumerId;
    }

    @Override
    public void onEvent(Order event) throws Exception {
        Thread.sleep(random.nextInt(5));
        System.out.println("当前消费者：" + this.consumerId +"，消费信息Id: " + event.getId());
        COUNT.incrementAndGet();
    }

    public int getCount() {
        return COUNT.get();
    }
}
