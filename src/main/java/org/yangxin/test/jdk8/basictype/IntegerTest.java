package org.yangxin.test.jdk8.basictype;

import java.util.Objects;

/**
 * @author yangxin
 * 2021/9/27 16:05
 */
@SuppressWarnings({"CommentedOutCode", "UnnecessaryBoxing", "ParameterCanBeLocal", "UnusedAssignment",
        "ConstantConditions", "AlibabaRemoveCommentedCode", "unused", "WrapperTypeMayBePrimitive", "NumberEquality"})
public class IntegerTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test6();
//        test7();
//        test8();
//        test9();
//        test10();
//        test11();
//        test12();
        test13();
    }

    private static void test13() {
        int i = 100_000;
        System.out.println(i);
    }

    private static void test12() {
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;

        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
    }

    private static void test11() {
        Integer intValue = 42;
        Byte byteValue = Byte.valueOf(intValue.byteValue());
        System.out.println(byteValue);
    }

    private static void test10() {
        // 创建一个 Integer 对象
        int intValue = 42;

        // 使用 Long.valueOf() 方法进行转换
        Long longValue = Long.valueOf(intValue);

        // 输出结果
        System.out.println("Integer Value: " + intValue);
        System.out.println("Long Value: " + longValue);
    }

    private static void test9() {
        Integer a = new Integer(2);
        final int b = 2;
        System.out.println(Objects.equals(a, b));
    }

    private static void test8() {
        // 完成掩码
        int DONE_MASK = 0xf0000000;  // mask out non-completion bits
        // 正常
        int NORMAL = 0xf0000000;  // must be negative
        // 取消的
        int CANCELLED = 0xc0000000;  // must be < NORMAL
        // 预期之外的
        int EXCEPTIONAL = 0x80000000;  // must be < CANCELLED

        // 信号的
        int SIGNAL = 0x00010000;  // must be >= 1 << 16
        // 掩码的
        int SMASK = 0x0000ffff;  // short bits for tags

        // 前四个值是负数（因为最高位是1）
        System.out.println(DONE_MASK);
        System.out.println(NORMAL);
        System.out.println(CANCELLED);
        System.out.println(EXCEPTIONAL);

        // 后两个值是正数，值为65536
        System.out.println(SIGNAL);
        System.out.println(SMASK);
    }

    private static void test7() {
        int a = 1;
        char b = (char) (a + '0');
        System.out.println(b);
        System.out.println(b - '0');
    }

    private static void test6() {
        Integer a = null;
//        System.out.println(a == 1);
        System.out.println(Objects.equals(a, 1));
    }

    private static void test4() {
        Integer a = new Integer(4);
        test5(a);
        System.out.println(a);
    }

    private static void test5(Integer a) {
        a = 1000000000;
    }

    private static void test3() {
        System.out.println(Integer.bitCount(1024 * 1024));
        System.out.println(Integer.bitCount(6));
    }

    /**
     * decode给定一个10进制，8进制，16进制中任何一种进制的字符串，该方法可以将传入的字符串转化为10进制数字的整型并返回。
     */
    private static void test2() {
        // 八进制
        String a = "010";
        // 十进制
        String b = "10";
        // 十六进制
        String c = "0x10";

        System.out.println(Integer.decode(a));
        System.out.println(Integer.decode(b));
        System.out.println(Integer.decode(c));
    }

    /**
     * toBinaryString，给定一个整型数据，返回这个数据的二进制字符串。
     * bitCount，给定一个整型数据，返回这个数据的二进制串中“1”的总数量。
     * numberOfLeadingZeros，给定一个整型数据，返回这个数据的二进制串中从最左边算起连续的“0”的总数量。
     * numberOfTrailingZeros，给定一个整型数据，返回这个数据的二进制串中从最右边算起连续的“0”的总数量。
     */
    private static void test1() {
        int x = 1, y = 2, z = 3;
        System.out.println(Integer.toBinaryString(x)
                + ":" + Integer.bitCount(x)
                + ":" + Integer.numberOfLeadingZeros(x)
                + ":" + Integer.numberOfTrailingZeros(x));
        System.out.println(Integer.toBinaryString(y)
                + ":" + Integer.bitCount(y)
                + ":" + Integer.numberOfLeadingZeros(y)
                + ":" + Integer.numberOfTrailingZeros(y));
        System.out.println(Integer.toBinaryString(z)
                + ":" + Integer.bitCount(z)
                + ":" + Integer.numberOfLeadingZeros(z)
                + ":" + Integer.numberOfTrailingZeros(z));
    }
}
