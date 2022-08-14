package org.yangxin.test.jdk8.io.nio;

import java.nio.ByteBuffer;

/**
 * @author yangxin
 * 2021/8/17 10:54
 */
public class ByteBufferTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        ByteBuffer buffer = ByteBuffer.allocate(128)
                .put((byte) 0x00)
                .put((byte) 0x00)
                .put((byte) 0x01)
                .put((byte) 0x9a);
        System.out.println(buffer.getInt());
    }
}
