package org.yangxin.test.datetime;

import java.sql.Timestamp;

/**
 * @author yangxin
 * 2021/6/9 10:28
 */
public class LongTest {

    public static void main(String[] args) {
        test1();
    }

    /**
     * 整型时间戳转时间戳类型
     */
    public static void test1() {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(new Timestamp((currentTimeMillis)));
    }
}
