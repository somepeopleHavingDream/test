package org.yangxin.test.disruptor.high;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Disruptor中的Event
 *
 * @author yangxin
 * 2022/1/3 13:40
 */
@Data
public class Trade {

    private String id;

    private String name;

    private double price;

    private AtomicInteger count = new AtomicInteger(0);
}
