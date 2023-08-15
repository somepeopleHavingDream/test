package org.yangxin.test.jdk8.datetime;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author yangxin
 * 2021/4/30 9:45
 */
@SuppressWarnings({"CommentedOutCode", "unused"})
public class LocalDateTimeTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test4();
//        test5();
//        test6();
//        test7();
//        test8();
        test9();
    }

    private static void test9() {
        LocalDateTime sourceDateTime = LocalDateTime.of(2023, 8, 14, 23, 41, 0);
        ZoneId sourceTimeZone = ZoneId.of("UTC+7");
        ZonedDateTime sourceZonedDateTime = ZonedDateTime.of(sourceDateTime, sourceTimeZone);
        System.out.println(sourceZonedDateTime);

        ZoneId targetTimeZone = ZoneId.of("GMT+7");
        ZonedDateTime targetZonedDateTime = sourceZonedDateTime.withZoneSameInstant(targetTimeZone);
        LocalDateTime targetDateTime = targetZonedDateTime.toLocalDateTime();
        System.out.println(targetDateTime);
        System.out.println(targetDateTime.getDayOfWeek());
    }

    private static void test8() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.with(TemporalAdjusters.firstDayOfNextMonth()).withHour(0).withMinute(0).withSecond(0);
//        LocalDateTime end = start.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).withHour(0).withMinute(0).withSecond(0);
        long millis = Duration.between(start, end).toMillis();

        System.out.println(start);
        System.out.println(end);
        System.out.println(millis);
    }

    private static void test7() {
        LocalDateTime start = LocalDateTime.now(ZoneId.of("GMT+3"));
        LocalDateTime end = start.plusDays(1).withHour(0).withMinute(0).withSecond(0);
        long millis = Duration.between(start, end).toMillis();

        System.out.println(start);
        System.out.println(end);
        System.out.println(millis);
    }

    private static void test6() {
        LocalDateTime expiredTime = LocalDateTime.of(2022, 12, 11, 10, 11, 58);
        LocalDateTime now = LocalDateTime.now();
    }

    /**
     * 获得当前一天剩余的秒数
     */
    private static void test5() {
        LocalDateTime tomorrowStart = LocalDateTime.now()
                .plusDays(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
        System.out.println(ChronoUnit.SECONDS.between(LocalDateTime.now(), tomorrowStart));
    }

    /**
     * LocalDateTime转Date
     */
    private static void test4() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
    }

    /**
     * LocalDateTime转Timestamp
     */
    private static void test3() {
        System.out.println(Timestamp.valueOf(LocalDateTime.now()));
    }

    /**
     * 格式化LocalDateTime
     */
    private static void test2() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        System.out.println(dateTimeFormatter.format(localDateTime));
    }

    /**
     * 时间比较
     */
    private static void test1() {
        LocalDateTime startLocalDateTime = LocalDateTime.of(2021, 4, 30, 10, 47, 27);
        LocalDateTime endLocalDateTime = LocalDateTime.of(2021, 4, 30, 10, 47, 55);

        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 20, 4, 3, 9);
        LocalDateTime cstLocalDateTime = localDateTime.plusHours(8);

        System.out.println(cstLocalDateTime.isEqual(startLocalDateTime));
        System.out.println(cstLocalDateTime.isAfter(startLocalDateTime));
        System.out.println(cstLocalDateTime.isBefore(endLocalDateTime));

        System.out.println(cstLocalDateTime.isEqual(startLocalDateTime)
                || (cstLocalDateTime.isAfter(startLocalDateTime)
                && cstLocalDateTime.isBefore(endLocalDateTime)));
    }
}
