package org.yangxin.test.disruptor.high.chain;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author yangxin
 * 2022/1/3 13:56
 */
public class TradePublisher implements Runnable {

    private final Disruptor<Trade> disruptor;
    private final CountDownLatch latch;

    private static final int PUBLISH_COUNT = 1;

    public TradePublisher(CountDownLatch latch, Disruptor<Trade> disruptor) {
        this.disruptor = disruptor;
        this.latch = latch;
    }

    @Override
    public void run() {
        TradeEventTranslator translator = new TradeEventTranslator();
        for (int i = 0; i < PUBLISH_COUNT; i++) {
            // 新的提交任务的方式
            disruptor.publishEvent(translator);
        }

        latch.countDown();
    }

    private static class TradeEventTranslator implements EventTranslator<Trade> {

        private final Random random = new Random();

        @Override
        public void translateTo(Trade event, long sequence) {
            this.generateTrade(event);
        }

        private void generateTrade(Trade event) {
            event.setPrice(random.nextDouble() * 9999);
        }
    }
}