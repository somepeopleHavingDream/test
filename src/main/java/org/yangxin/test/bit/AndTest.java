package org.yangxin.test.bit;

/**
 * @author yangxin
 * 2021/11/12 17:36
 */
public class AndTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        System.out.println(11 & 11 - 1);
        System.out.println((11 & 11 - 1) != 0);

        System.out.println(10 & 10 - 1);
        System.out.println((10 & 10 - 1) != 0);

        System.out.println(8192 & 8192 - 1);
        System.out.println((8192 & 8192 - 1) != 0);

        System.out.println(4 & 4 - 1);
        System.out.println((4 & 4 - 1) != 0);
    }
}
