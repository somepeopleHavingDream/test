package org.yangxin.test.jdk8.datetime;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author yangxin
 * 2024/4/19 11:08
 */
public class ZoneDateTimeTest {

    public static void main(String[] args) {
        test1();
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
