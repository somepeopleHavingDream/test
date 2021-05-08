package org.yangxin.test.datetime;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;

/**
 * Date
 *
 * @author yangxin
 * 2020/02/12 11:39
 */
public class DateTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    /**
     * Date转LocalDate
     */
    private static void test2() {
        Date date = new Date();
        System.out.println(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    /**
     * 格式化Date
     */
    private static void test1() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(date));
        System.out.println(date);
    }
}
