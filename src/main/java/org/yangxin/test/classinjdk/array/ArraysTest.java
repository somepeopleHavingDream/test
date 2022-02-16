package org.yangxin.test.classinjdk.array;

import java.util.Arrays;

/**
 * @author yangxin
 * 2021/10/13 9:28
 */
public class ArraysTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        int[] num = {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(Arrays.copyOfRange(num, 0, 1)));
    }

    private static void test1() {
        String[] array = new String[10];
        Arrays.fill(array, "hello world");
        System.out.println(Arrays.toString(array));
    }
}
