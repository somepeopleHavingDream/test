package org.yangxin.test.jdk8.datetime;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author yangxin
 * 2024/4/19 11:08
 */
@SuppressWarnings("unused")
public class ZoneDateTimeTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        // 假设有一个 ZonedDateTime 对象
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("GMT+3"));

        // 将 ZonedDateTime 转换为 LocalDateTime
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

        // 输出结果
        System.out.println("ZonedDateTime: " + zonedDateTime);
        System.out.println("LocalDateTime: " + localDateTime);
    }

    private static void test1() {
        String dateStr = ZonedDateTime
                .now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(dateStr);

        long timestamp = ZonedDateTime
                .now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .truncatedTo(java.time.temporal.ChronoUnit.DAYS)
                .toInstant()
                .toEpochMilli();
        System.out.println(timestamp);
    }
}
