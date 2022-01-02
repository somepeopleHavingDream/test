package org.yangxin.test.disruptor.ability;

import com.lmax.disruptor.EventHandler;

/**
 * @author yangxin
 * 2022/1/2 17:39
 */
public class DataConsumer implements EventHandler<Data> {

    private final long startTime;
    private int i;

    public DataConsumer() {
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void onEvent(Data data, long l, boolean b) throws Exception {
        i++;
        if (i == Constants.EVENT_NUM_OHM) {
            long endTime = System.currentTimeMillis();
            System.out.println("Disruptor costTime = " + (endTime - startTime) + "ms");
        }
    }
}
