package org.yangxin.test.jdk8.math;

/**
 * @author yangxin
 * 2022/9/29 18:28
 */
public class MathTest {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    private static void test3() {
        long a = Long.MAX_VALUE;
        System.out.println(Math.toIntExact(a));
    }

    private static void test2() {
        long a = -829;
        System.out.println(Math.toIntExact(a));
    }

    private static void test1() {
        long a = 230;
        System.out.println(Math.toIntExact(a));
    }
}
