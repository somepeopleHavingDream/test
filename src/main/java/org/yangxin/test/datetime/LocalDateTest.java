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
@SuppressWarnings({"CommentedOutCode", "AlibabaUndefineMagicConstant"})
public class LocalDateTest {

    public static void main(String[] args) {
//        testDateTimeFormatter();
//        testTimeBetween();

        LocalDate localDate = LocalDate.now();
        for (int i = 1; i <= 21; i++) {
            System.out.println(localDate.plusDays(i));
            System.out.println(localDate);
        }
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

    /**
     * 格式化LocalDateTime
     */
    private static void testDateTimeFormatter() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormatter.format(localDateTime));
    }
}
