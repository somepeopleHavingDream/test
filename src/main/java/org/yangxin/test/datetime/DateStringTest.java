package org.yangxin.test.datetime;

import org.yangxin.test.list.Simple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时期字符串案例
 *
 * @author yangxin
 * 2021/5/27 11:35
 */
public class DateStringTest {

    public static void main(String[] args) throws ParseException {
        test1();
    }

    /**
     * 字符串转Date
     *
     * @throws ParseException 解析异常
     */
    private static void test1() throws ParseException {
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmm");
        String sendTime = "202105261542";
        Date date = simpleDateFormat2.parse(sendTime);
        System.out.println(date);

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("YYYY-MM-DD HH24:MI");
        System.out.println(simpleDateFormat1.format(date));
    }

    /**
     * String转LocalDateTime
     */
    private static void test2() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        String stuTime1 =  "2020/10/25 01:52:59";
        LocalDateTime localDateTime1 = LocalDateTime.parse(stuTime1, dateTimeFormatter);

        String stuTime2 =  "2020/10/25 01:52:59";
        LocalDateTime localDateTime2 = LocalDateTime.parse(stuTime2, dateTimeFormatter);

        System.out.println(localDateTime1.isEqual(localDateTime2));
    }

    /**
     * String转LocalDateTime
     */
    private static void test3() {
        String stuTime =  "2020/10/25 01:52:59";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(stuTime, dateTimeFormatter);
        System.out.println(localDateTime.plusHours(8));
    }

    /**
     * 时间戳字符串转LocalDateTime
     */
    private static void test4() {
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
}
