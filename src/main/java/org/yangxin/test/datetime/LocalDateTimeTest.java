package org.yangxin.test.datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yangxin
 * 2021/4/30 9:45
 */
@SuppressWarnings("CommentedOutCode")
public class LocalDateTimeTest {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    /**
     * 时间比较
     */
    private static void test3() {
        LocalDateTime startLocalDateTime = LocalDateTime.of(2021, 4, 30, 10, 47, 27);
        LocalDateTime endLocalDateTime = LocalDateTime.of(2021, 4, 30, 10, 47, 55);

        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 20, 4, 3, 9);
        LocalDateTime cstLocalDateTime = localDateTime.plusHours(8);

        System.out.println(cstLocalDateTime.isEqual(startLocalDateTime));
        System.out.println(cstLocalDateTime.isAfter(startLocalDateTime));
        System.out.println(cstLocalDateTime.isBefore(endLocalDateTime));

        System.out.println(cstLocalDateTime.isEqual(startLocalDateTime)
                || (cstLocalDateTime.isAfter(startLocalDateTime)
                && cstLocalDateTime.isBefore(endLocalDateTime)));
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
    private static void test1() {
        String stuTime =  "2020/10/25 01:52:59";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(stuTime, dateTimeFormatter);
        System.out.println(localDateTime.plusHours(8));
    }
}
