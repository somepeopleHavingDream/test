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
