package org.yangxin.test.jdk8.datetime;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * @author yangxin
 * 2022/8/19 13:47
 */
public class InstantTest {

    public static void main(String[] args) {
        // 获取一个本初子午线上的标准时间
        Instant instant = Instant.now();
        // 加8个时区
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
    }
}
