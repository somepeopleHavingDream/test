package org.yangxin.test.spring.getbeansoftype;

import org.springframework.stereotype.Component;

/**
 * 汽车方式
 *
 * @author yangxin
 * 2021/10/18 11:58
 */
@Component
public class BusMode implements TrafficMode {

    @Override
    public TrafficCode getCode() {
        return TrafficCode.BUS;
    }

    @Override
    public Integer getFee() {
        return 10000;
    }
}
