package org.yangxin.test.shift;

import java.nio.channels.SelectionKey;

/**
 * @author yangxin
 * 2021/10/13 17:22
 */
public class ShiftTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        int i = SelectionKey.OP_CONNECT | SelectionKey.OP_ACCEPT | SelectionKey.OP_READ | SelectionKey.OP_WRITE;
        System.out.println(i);
    }
}
