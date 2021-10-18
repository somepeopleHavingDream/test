package org.yangxin.test.spring.getbeansoftype;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author yangxin
 * 2021/10/18 14:13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class TrafficModeFactoryTest {

    @Test
    void getTrafficMode() {
        TrafficMode mode = TrafficModeFactory.getTrafficMode(TrafficCode.BUS);
        assertEquals(mode.getFee().intValue(), 10000);
    }
}