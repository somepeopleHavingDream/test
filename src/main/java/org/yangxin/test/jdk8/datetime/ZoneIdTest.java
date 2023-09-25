package org.yangxin.test.jdk8.datetime;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author yangxin
 * 2023/7/12 18:36
 */
@SuppressWarnings("unused")
public class ZoneIdTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        // 明确指定时区为东八区（中国标准时间）
        ZoneId chinaTimeZone = ZoneId.of("GMT+7");

        // 获取当前时间和时区
        ZonedDateTime currentDateTime = ZonedDateTime.now(chinaTimeZone);

        // 获取当前时间的星期几
        System.out.println(currentDateTime.getDayOfWeek());
        System.out.println(currentDateTime);
    }

    private static void test1() {
        // 获取当前时间
        Date now = new Date();
        ZonedDateTime zonedDateTime = now.toInstant().atZone(ZoneId.of("GMT+7"));
        System.out.println(zonedDateTime.getDayOfWeek() == DayOfWeek.THURSDAY);
        System.out.println(zonedDateTime);
    }
}
