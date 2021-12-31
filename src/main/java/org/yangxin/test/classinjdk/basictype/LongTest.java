package org.yangxin.test.classinjdk.basictype;

/**
 * @author yangxin
 * 2021/8/5 14:17
 */
public class LongTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        System.out.println(10000000303L % 30);
    }

    private static void test1() {
        System.out.println(0xFFL);
        System.out.println(0xFF);
    }
}
