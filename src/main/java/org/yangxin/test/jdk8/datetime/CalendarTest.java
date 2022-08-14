package org.yangxin.test.jdk8.datetime;

import java.util.Calendar;

/**
 * 日历
 *
 * @author yangxin
 * 2019/11/29 14:09
 */
public class CalendarTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.toString());
    }
}
