package org.yangxin.test.disruptor.high;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yangxin
 * 2022/1/3 13:44
 */
@SuppressWarnings({"AlibabaThreadPoolCreation", "deprecation"})
public class Main {

    public static void main(String[] args) throws InterruptedException {
        // 构建一个线程池用于提交任务
        long begin = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // 1 构建Disruptor
        Disruptor<Trade> disruptor = new Disruptor<>(Trade::new,
                1024 * 1024,
                Executors.newFixedThreadPool(4),
                ProducerType.SINGLE,
                new BusySpinWaitStrategy());

        // 2 把消费者设置到Disruptor的handleEventWith中
        // 3 启动Disruptor
        RingBuffer<Trade> ringBuffer = disruptor.start();

        CountDownLatch latch = new CountDownLatch(1);
        executorService.submit(new TradePublisher(latch, disruptor));

        latch.await();

        disruptor.shutdown();
        executorService.shutdown();
        System.out.println("总耗时：" + (System.currentTimeMillis() - begin));
    }
}
