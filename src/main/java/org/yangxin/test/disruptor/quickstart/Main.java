package org.yangxin.test.disruptor.quickstart;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author yangxin
 * 2022/1/2 19:14
 */
@SuppressWarnings({"AlibabaThreadPoolCreation", "AlibabaRemoveCommentedCode", "CommentedOutCode", "deprecation", "AlibabaUndefineMagicConstant"})
public class Main {

    public static void main(String[] args) {
        // 参数准备工作
        OrderEventFactory orderEventFactory = new OrderEventFactory();
        int ringBufferSize = 1024 * 1024;
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        ThreadFactory threadFactory = r -> {
//            Thread thread = new Thread(r);
//            thread.setName("DisruptorQuickStartThread");
//            return thread;
//        };

        /*
            1 eventFactory 消息（event）工厂对象
            2 ringBufferSize 容器的长度
            3 executor 线程池（建议使用自定义线程池）RejectedExecutionHandler
            4 ProducerType 单生产者还是多生产者
            5 waitStrategy 等待策略
         */
        // 1 实例化disruptor对象
        Disruptor<OrderEvent> disruptor = new Disruptor<>(orderEventFactory,
                ringBufferSize,
                executorService,
                ProducerType.SINGLE,
                new BlockingWaitStrategy());

        // 2 添加消费者的监听（构建disruptor与消费者的一个关联关系）
        disruptor.handleEventsWith(new OrderEventHandler());

        // 3 启动disruptor
        disruptor.start();

        // 4 获取实际存储数据的容器：RingBuffer
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
        OrderEventProducer producer = new OrderEventProducer(ringBuffer);

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 0; i < 100; i++) {
            byteBuffer.putLong(0, i);
            producer.sendData(byteBuffer);
        }

        disruptor.shutdown();
        executorService.shutdown();
    }
}
