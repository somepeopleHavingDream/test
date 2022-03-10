package org.yangxin.test.classinjdk.basictype;

import java.util.Objects;

/**
 * @author yangxin
 * 2021/9/27 16:05
 */
@SuppressWarnings({"CommentedOutCode", "UnnecessaryBoxing", "ParameterCanBeLocal", "UnusedAssignment", "ConstantConditions"})
public class IntegerTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
        test6();
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
        a = 5;
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
