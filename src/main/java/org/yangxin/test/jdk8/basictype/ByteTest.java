package org.yangxin.test.jdk8.basictype;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author yangxin
 * 2021/8/5 9:24
 */
@SuppressWarnings({"EqualsWithItself", "unused"})
public class ByteTest {

    public static void main(String[] args) {
//        test1();
        test2();
//        test3();
    }

    private static void test3() {
        System.out.println(Objects.equals((byte)1, (byte) 1));
    }

    /**
     * 字节数组转字符串
     */
    private static void test1() {
        byte[] header = new byte[]{7, 7, 7, 7, 7, 7, 7, 7};
        System.out.println(Arrays.toString(header));
    }

    private static void test2() {
        byte a = 1;
        System.out.println(a);
    }
}
