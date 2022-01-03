package org.yangxin.test.disruptor.high.multi;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;

/**
 * @author yangxin
 * 2022/1/3 15:38
 */
public class Main {

    public static void main(String[] args) {
        // 1 创建RingBuffer
        RingBuffer<Order> ringBuffer = RingBuffer.create(ProducerType.MULTI,
                Order::new,
                1024 * 1024,
                new YieldingWaitStrategy());

        // 2 通过ringBuffer创建一个屏障
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        // 3 创建多个消费者的数组
        Consumer[] consumers = new Consumer[10];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer("C" + i);
        }

        // 4 构建多消费者工作池
        WorkerPool<Order> workerPool = new WorkerPool<>(ringBuffer,
                sequenceBarrier,
                new EventExceptionHandler(),
                consumers);
    }

    private static class EventExceptionHandler implements ExceptionHandler<Order> {

        @Override
        public void handleEventException(Throwable ex, long sequence, Order event) {
        }

        @Override
        public void handleOnStartException(Throwable ex) {
        }

        @Override
        public void handleOnShutdownException(Throwable ex) {
        }
    }
}
