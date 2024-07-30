package org.yangxin.test.jdk8.datetime;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author yangxin
 * 2024/7/9 15:41
 */
public class ChronoUnitTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        LocalDate date1 = LocalDate.of(2023, 1, 1);
        LocalDate date2 = LocalDate.of(2023, 7, 1);

        System.out.println(ChronoUnit.DAYS.between(date1, date2));
        System.out.println(ChronoUnit.DAYS.between(date2, date1));
    }
}
