package org.yangxin.test.disruptor.high.chain.consumer;

import com.lmax.disruptor.EventHandler;
import org.yangxin.test.disruptor.high.chain.Trade;

import java.util.UUID;

/**
 * @author yangxin
 * 2022/1/3 14:11
 */
public class Handler2 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("handler 2 : SET ID");

        event.setId(UUID.randomUUID().toString());
        Thread.sleep(2000);
    }
}
