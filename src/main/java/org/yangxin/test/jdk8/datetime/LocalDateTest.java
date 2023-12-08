package org.yangxin.test.jdk8.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
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
//        test1();
        test2();
    }

    private static void test2() {
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        System.out.println("This Monday's date is: " + monday);
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
