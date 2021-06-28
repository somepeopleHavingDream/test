package org.yangxin.test.datetime;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author yangxin
 * 2021/6/9 10:28
 */
public class LongTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    /**
     * 整型时间戳转时间戳类型
     */
    public static void test1() {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(new Timestamp(currentTimeMillis));
    }

    /**
     * 整型时间戳转日期字符串
     */
    public static void test2() {
        long timestamp = 1623035797428L;
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timestamp / 1000,
                0, ZoneOffset.ofHours(8));

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        System.out.println(dateTimeFormatter.format(localDateTime));

//        System.out.println(Instant.ofEpochSecond(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime());
    }
}
