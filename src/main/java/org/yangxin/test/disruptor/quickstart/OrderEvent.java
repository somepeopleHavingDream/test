package org.yangxin.test.disruptor.quickstart;

import lombok.Data;

/**
 * @author yangxin
 * 2022/1/2 19:03
 */
@Data
public class OrderEvent {

    /**
     * 订单的价格
     */
    private long value;
}
