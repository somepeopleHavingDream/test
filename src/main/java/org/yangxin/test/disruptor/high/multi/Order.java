package org.yangxin.test.disruptor.high.multi;

import lombok.Data;

/**
 * Disruptor中的Event
 *
 * @author yangxin
 * 2022/1/3 13:40
 */
@Data
public class Order {

    private String id;

    private String name;

    private double price;
}
