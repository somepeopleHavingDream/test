package org.yangxin.test.jdk8.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yangxin
 * 2024/8/21 14:54
 */
public class DateTimeFormatterTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        // 时间字符串
        String timeStr = "2024-08-12 15:30:00";

        // 创建格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 将字符串解析为 LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(timeStr, formatter);

        // 指定时区，例如 "Asia/Shanghai"
        ZoneId targetZoneId = ZoneId.of("GMT+3");

        // 转换为指定时区的时间
        ZonedDateTime zonedDateTime = localDateTime.atZone(targetZoneId);

        // 输出转换后的时间
        System.out.println("Original time: " + timeStr);
        System.out.println("Converted time in " + targetZoneId + ": " + zonedDateTime);
        System.out.println(zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime());
    }
}
