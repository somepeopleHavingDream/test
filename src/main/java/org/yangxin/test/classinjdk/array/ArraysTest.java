package org.yangxin.test.classinjdk.array;

import java.util.Arrays;

/**
 * @author yangxin
 * 2021/10/13 9:28
 */
public class ArraysTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        String[] array = new String[10];
        Arrays.fill(array, "hello world");
        System.out.println(Arrays.toString(array));
    }
}
