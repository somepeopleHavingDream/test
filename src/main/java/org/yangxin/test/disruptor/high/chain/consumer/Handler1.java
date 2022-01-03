package org.yangxin.test.disruptor.high.chain.consumer;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import org.yangxin.test.disruptor.high.chain.Trade;

/**
 * @author yangxin
 * 2022/1/3 14:11
 */
public class Handler1 implements EventHandler<Trade>, WorkHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    @Override
    public void onEvent(Trade event) throws Exception {
        System.out.println("handler 1 : SET NAME");

        event.setName("H1");
        Thread.sleep(1000);
    }
}
