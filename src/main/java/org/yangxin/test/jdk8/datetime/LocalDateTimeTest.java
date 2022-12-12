package org.yangxin.test.jdk8.datetime;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
        test6();
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
