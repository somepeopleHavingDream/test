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
@SuppressWarnings({"AlibabaUseRightCaseForDateFormat", "SuspiciousDateFormat"})
public class DateTest {

    public static void main(String[] args) {
        test1();
//        test2();
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

        System.out.println(date);

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat1.format(date));

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("YYYYMMDDHHMMSS");
        System.out.println(simpleDateFormat2.format(date));
    }
}
