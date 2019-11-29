package org.yangxin.test.datetime;

import java.time.LocalDate;

/**
 * LocalDate
 *
 * @author yangxin
 * 2019/11/29 14:14
 */
public class LocalDateTest {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        String fromTime = now.minusDays(1).toString();
        String toTime = now.toString();

        System.out.println(fromTime);
        System.out.println(toTime);
    }
}
