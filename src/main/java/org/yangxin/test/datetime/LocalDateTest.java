package org.yangxin.test.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * LocalDate
 *
 * @author yangxin
 * 2019/11/29 14:14
 */
public class LocalDateTest {

    public static void main(String[] args) {
//        testDateTimeFormatter();

        // 时间范围
        LocalDate startLocalDate = LocalDate.parse("2016-02-23");
        LocalDate endLocalDate = LocalDate.parse("2020-03-03");
        System.out.println(startLocalDate + "-" + endLocalDate);
        System.out.println(Period.between(startLocalDate, endLocalDate).getMonths());
        System.out.println(ChronoUnit.MONTHS.between(startLocalDate, endLocalDate));
    }

    private static void testDateTimeFormatter() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormatter.format(localDateTime));
    }
}
