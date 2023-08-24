package org.yangxin.test.spring.context.getbeansoftype;

import org.springframework.stereotype.Component;

/**
 * 火车方式
 *
 * @author yangxin
 * 2021/10/18 11:59
 */
@Component
public class TrainMode implements TrafficMode {

    @Override
    public TrafficCode getCode() {
        return TrafficCode.TRAIN;
    }

    @Override
    public Integer getFee() {
        return 9000;
    }
}
