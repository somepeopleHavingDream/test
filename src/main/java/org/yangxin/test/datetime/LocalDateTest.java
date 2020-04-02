package org.yangxin.test.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalDate
 *
 * @author yangxin
 * 2019/11/29 14:14
 */
public class LocalDateTest {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
//        String fromTime = now.minusDays(1).toString();
//        String toTime = now.toString();
//
//        System.out.println(fromTime);
//        System.out.println(toTime);

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        System.out.println(dateTimeFormatter1.format(localDateTime));
//        System.out.println(dateTimeFormatter2.format(localDateTime));
    }
}
