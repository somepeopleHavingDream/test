package org.yangxin.test.jdk8.datetime;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Date
 *
 * @author yangxin
 * 2020/02/12 11:39
 */
@SuppressWarnings({"AlibabaUseRightCaseForDateFormat", "SuspiciousDateFormat", "CommentedOutCode", "deprecation", "unused", "ConstantValue"})
public class DateTest {

    /**
     * 每秒毫秒数
     */
    private static final long MILLS_PER_SECOND = 1000;

    /**
     * 每分钟毫秒数
     */
    private static final long MILLS_PER_MINUTE = MILLS_PER_SECOND * 60;

    /**
     * 每小时毫秒数
     */
    private static final long MILLS_PER_HOUR = MILLS_PER_MINUTE * 60;

    /**
     * 每天毫秒数
     */
    private static final long MILLS_PER_DAY = MILLS_PER_HOUR * 24;

    /**
     * 每周毫秒数
     */
    private static final long MILLS_PER_WEEK = MILLS_PER_DAY * 7;

    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
    }

    /**
     * Date 转 ZonedDateTime
     */
    private static void test6() {
        // 创建一个 Date 对象
        Date date = new Date();

        // 将 Date 转换为 Instant
        Instant instant = date.toInstant();

        // 指定要转换到的时区
        ZoneId zoneId = ZoneId.of("GMT+3");

        // 将 Instant 转换为 ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);

        // 输出结果
        System.out.println("Date: " + date);
        System.out.println("ZonedDateTime: " + zonedDateTime);
    }

    /**
     * 比较两个日期
     */
    private static void test5() {
        Date date1 = new Date(1675671596000L);
        Date date2 = new Date(1675671596000L);

        System.out.println(date1);
        System.out.println(date2);
        System.out.println(Objects.equals(date1, date2));
    }

    /**
     * Date 转 LocalDate
     */
    private static void test4() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(localDate);

        LocalDate left = LocalDate.of(2022, 9, 1);
        System.out.println(left);

        System.out.println(localDate.isBefore(left));
        System.out.println(left.isBefore(left));
        System.out.println(left.isAfter(left));
    }

    /**
     * 判断时间段是否在某一范围内
     *
     * @throws InterruptedException 被中断的异常
     */
    private static void test3() throws InterruptedException {
        Date d1 = new Date();
        TimeUnit.SECONDS.sleep(5);
        Date d2 = new Date();

        System.out.println(d2.getTime() - d1.getTime() < MILLS_PER_WEEK);

        Date d3 = new Date(2022, Calendar.SEPTEMBER, 16);
        System.out.println(d3.getTime() - d2.getTime() < MILLS_PER_WEEK);
    }

    /**
     * Date 转 LocalDateTime
     */
    private static void test2() {
        Date date = new Date();
        System.out.println(date);
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(localDateTime);
        System.out.println(DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(localDateTime));
    }

    /**
     * 格式化 Date
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
