package org.yangxin.test.datetime;

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
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormatter.format(localDateTime));
//        System.out.println(dateTimeFormatter2.format(localDateTime));
    }
}
