package org.yangxin.test.jdk8.datetime;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author yangxin
 * 2023/7/12 18:36
 */
public class ZoneIdTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        // 获取当前时间
        Date now = new Date();
        ZonedDateTime zonedDateTime = now.toInstant().atZone(ZoneId.of("GMT+7"));
        System.out.println(zonedDateTime.getDayOfWeek() == DayOfWeek.THURSDAY);
    }
}
