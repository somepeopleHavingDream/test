package org.yangxin.test.jdk8.basictype;

/**
 * @author yangxin
 * 2021/9/13 10:05
 */
public class ShortTest {

    public static void main(String[] args) {
        test1();
    }

    /**
     * short转byte
     */
    private static void test1() {
        short a = 1200;
        System.out.println((byte) a);
    }
}
