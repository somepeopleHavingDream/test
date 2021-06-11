package org.yangxin.test.datetime;

import java.sql.Timestamp;
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
        test1();
    }

    /**
     * 获得当前时间戳
     */
    private static void getTimeStamp() {
        Date date = new Date();
        System.out.println(date.getTime());
        System.out.println(System.currentTimeMillis());
    }

    /**
     * 时间戳转日期字符串
     */
    private static void test1() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        System.out.println(localDateTime);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        System.out.println(dateTimeFormatter.format(localDateTime));
    }
}
