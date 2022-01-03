package org.yangxin.test.disruptor.high.chain.consumer;

import com.lmax.disruptor.EventHandler;
import org.yangxin.test.disruptor.high.chain.Trade;

/**
 * @author yangxin
 * 2022/1/3 14:11
 */
public class Handler5 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("handler 5 : GET PRICE: " + event.getPrice());
        event.setPrice(event.getPrice() + 3.0);
    }
}
