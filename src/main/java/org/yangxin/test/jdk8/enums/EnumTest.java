package org.yangxin.test.jdk8.enums;

/**
 * @author yangxin
 * 2021/10/15 17:17
 */
public class EnumTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        for (Light constant : Light.class.getEnumConstants()) {
            System.out.println(constant);
        }
    }

    private static void test1() {
        System.out.println(Light.GREEN.ordinal());
    }
}
