package org.yangxin.test.datetime;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yangxin
 * 2021/4/30 9:45
 */
public class LocalDateTimeTest {

    public static void main(String[] args) {
//        test1();
        test3();
    }

    /**
     * 时间比较
     */
    private static void test1() {
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
     * 格式化LocalDateTime
     */
    private static void test2() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormatter.format(localDateTime));
    }

    private static void test3() {
        System.out.println(Timestamp.valueOf(LocalDateTime.now()));
    }
}
