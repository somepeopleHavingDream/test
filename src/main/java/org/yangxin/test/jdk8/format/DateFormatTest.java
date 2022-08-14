package org.yangxin.test.jdk8.format;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author yangxin
 * 2022/1/15 16:08
 */
public class DateFormatTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Locale locale = new Locale("en", "US");
        Date date = new Date();
        DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        System.out.println(format.format(date));
    }
}
