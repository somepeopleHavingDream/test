package org.yangxin.test.disruptor.high.multi;

import com.lmax.disruptor.RingBuffer;

/**
 * @author yangxin
 * 2022/1/3 16:09
 */
public class Producer {

    private final RingBuffer<Order> ringBuffer;

    public Producer(RingBuffer<Order> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(String uuid) {
        long sequence = ringBuffer.next();

        try {
            Order order = ringBuffer.get(sequence);
            order.setId(uuid);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
