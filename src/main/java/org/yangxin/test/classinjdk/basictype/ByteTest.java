package org.yangxin.test.classinjdk.basictype;

import java.util.Arrays;

/**
 * @author yangxin
 * 2021/8/5 9:24
 */
public class ByteTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        byte[] header = new byte[]{7, 7, 7, 7, 7, 7, 7, 7};
        System.out.println(Arrays.toString(header));
    }
}
