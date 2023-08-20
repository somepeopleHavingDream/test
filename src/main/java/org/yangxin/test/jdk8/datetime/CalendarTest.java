package org.yangxin.test.jdk8.datetime;

import java.util.Calendar;

/**
 * 日历
 *
 * @author yangxin
 * 2019/11/29 14:09
 */
@SuppressWarnings("unused")
public class CalendarTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.getTime());
        System.out.println(instance.get(Calendar.WEEK_OF_YEAR));

        instance.add(Calendar.WEEK_OF_YEAR, -1);
        System.out.println(instance.getTime());
    }

    private static void test1() {
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.toString());
    }
}
