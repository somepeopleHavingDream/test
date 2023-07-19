package org.yangxin.test.jdk8.datetime;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * LocalDate
 *
 * @author yangxin
 * 2019/11/29 14:14
 */
@SuppressWarnings({"AlibabaUndefineMagicConstant", "unused", "CommentedOutCode"})
public class LocalDateTest {

    public static void main(String[] args) {
//        testTimeBetween();
//        test3();
        test1();
    }

    private static void test1() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate today = LocalDate.now();

        ZonedDateTime yesterdayZonedDateTime = yesterday.atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime todayZonedDateTime = today.atStartOfDay(ZoneId.systemDefault());

        System.out.println(Date.from(yesterdayZonedDateTime.toInstant()));
        System.out.println(Date.from(todayZonedDateTime.toInstant()));
    }

    /**
     * LocalDate转Date
     */
    private static void test3() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        System.out.println(formatter.format(localDate));

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        System.out.println(Date.from(zonedDateTime.toInstant()));
    }

    /**
     * 两个时间点间相差多少个时间单位
     */
    private static void testTimeBetween() {
        // 时间范围
        LocalDate startLocalDate = LocalDate.parse("2016-02-23");
        LocalDate endLocalDate = LocalDate.parse("2020-03-03");
        System.out.println(startLocalDate + "-" + endLocalDate);
        System.out.println(Period.between(startLocalDate, endLocalDate).getMonths());
        System.out.println(ChronoUnit.MONTHS.between(startLocalDate, endLocalDate));
    }
}
