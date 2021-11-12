package org.yangxin.test.bit;

import java.nio.channels.SelectionKey;

/**
 * @author yangxin
 * 2021/10/13 17:22
 */
public class ShiftTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        System.out.println(Integer.numberOfLeadingZeros(0x0000ffff));
    }

    private static void test1() {
        int i = SelectionKey.OP_CONNECT | SelectionKey.OP_ACCEPT | SelectionKey.OP_READ | SelectionKey.OP_WRITE;
        System.out.println(i);
    }
}
