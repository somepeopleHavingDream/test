package org.yangxin.test.jdk8.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * 时期字符串案例
 *
 * @author yangxin
 * 2021/5/27 11:35
 */
@SuppressWarnings({"unused", "CommentedOutCode"})
public class DateStringTest {

    public static void main(String[] args) throws ParseException {
//        test1();
//        test5();
        test6();
    }

    private static void test6() {
        // 输入的时间和时区
        String time = "2024-08-29 00:00:00";
        String timezone = "GMT+3";
        int hoursToAdd = 5; // 需要添加的小时数

        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 将输入时间解析为LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);

        // 将LocalDateTime转换为ZonedDateTime
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(timezone));

        // 添加指定的小时数
        ZonedDateTime updatedTime = zonedDateTime.plusHours(hoursToAdd);

        // 输出结果
        System.out.println("原时间: " + zonedDateTime);
        System.out.println("添加 " + hoursToAdd + " 小时后的时间: " + updatedTime);

        System.out.println(Date.from(updatedTime.toInstant()));

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

    private static void test5() {
        Date date = new Date();

        // 创建“简体中文”的本地化
        Locale localeCn = Locale.SIMPLIFIED_CHINESE;
        // 创建“英文/美国”的本地化
        Locale localeUs = new Locale("en", "US");

        // 获取“简体中文”对应的date字符串
        String cn = DateFormat.getDateInstance(DateFormat.MEDIUM, localeCn).format(date);
        // 获取“英文/美国”对应的date字符串
        String us = DateFormat.getDateInstance(DateFormat.MEDIUM, localeUs).format(date);

        System.out.printf("cn=%s\nus=%s\n", cn, us);
    }
}