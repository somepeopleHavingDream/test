package org.yangxin.test.classinjdk.datetime;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间戳
 *
 * @author yangxin
 * 2019/11/19 14:38
 */
@SuppressWarnings("CommentedOutCode")
public class TimeStampTest {

    public static void main(String[] args) {
//        test1();
//        test2();
    }

    /**
     * 获得当前时间戳
     */
    private static void test2() {
        Date date = new Date();
        System.out.println(date.getTime());
        System.out.println(System.currentTimeMillis());
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
