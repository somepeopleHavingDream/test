package org.yangxin.test.datetime;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间戳
 *
 * @author yangxin
 * 2019/11/19 14:38
 */
public class TimeStampTest {

    public static void main(String[] args) {
//        getTimeStamp();

        test2();
    }

    /**
     * 时间戳字符串转LocalDateTime
     */
    private static void test2() {
        String timestamp = "1618780339154";
        System.out.println(timestamp);
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);

        Date date = new Date(Long.parseLong(timestamp));
        System.out.println(date);

        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(Long.parseLong(timestamp) / 1000,
                0, ZoneOffset.ofHours(8));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(dateTimeFormatter.format(localDateTime));
    }

    /**
     * 获得当前时间戳
     */
    private static void getTimeStamp() {
        Date date = new Date();
        System.out.println(date.getTime());
        System.out.println(System.currentTimeMillis());
    }
}
