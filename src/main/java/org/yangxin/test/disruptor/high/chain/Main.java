package org.yangxin.test.disruptor.high.chain;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.yangxin.test.disruptor.high.chain.consumer.Handler1;
import org.yangxin.test.disruptor.high.chain.consumer.Handler2;
import org.yangxin.test.disruptor.high.chain.consumer.Handler3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yangxin
 * 2022/1/3 13:44
 */
@SuppressWarnings({"AlibabaThreadPoolCreation", "deprecation", "AlibabaRemoveCommentedCode"})
public class Main {

    public static void main(String[] args) throws InterruptedException {
        // 构建一个线程池用于提交任务，构建另一个线程池用于Disruptor
        long begin = System.currentTimeMillis();
        ExecutorService executorService1 = Executors.newFixedThreadPool(4);
        ExecutorService executorService2 = Executors.newFixedThreadPool(4);

        // 1 构建Disruptor
        Disruptor<Trade> disruptor = new Disruptor<>(Trade::new,
                1024 * 1024,
                executorService2,
                ProducerType.SINGLE,
                new BusySpinWaitStrategy());

        // 2 把消费者设置到Disruptor的handleEventWith中
        // 2.1 串行操作
//        disruptor.handleEventsWith(new Handler1())
//                .handleEventsWith(new Handler2())
//                .handleEventsWith(new Handler3());

        // 2.2 并行操作
//        disruptor.handleEventsWith(new Handler1(), new Handler2(), new Handler3());

        // 2.3 菱形操作（一）
//        disruptor.handleEventsWith(new Handler1(), new Handler2()).handleEventsWith(new Handler3());
        // 2.4 菱形操作（二）
        disruptor.handleEventsWith(new Handler1(), new Handler2()).then(new Handler3());

        // 3 启动Disruptor
        RingBuffer<Trade> ringBuffer = disruptor.start();

        CountDownLatch latch = new CountDownLatch(1);
        executorService1.submit(new TradePublisher(latch, disruptor));

        latch.await();

        disruptor.shutdown();
        executorService1.shutdown();
        executorService2.shutdown();
        System.out.println("总耗时：" + (System.currentTimeMillis() - begin));
    }
}
