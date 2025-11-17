package org.yangxin.test.jdk8.io.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author yangxin
 * 2021/8/17 10:54
 */
@SuppressWarnings("unused")
public class ByteBufferTest {

    public static void main(String[] args) throws CharacterCodingException {
//        test1();
//        test2();
        test3();
    }

    private static void test3() {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        buffer.position(2);
        buffer.limit(5);

        int position = buffer.position();
        int limit = buffer.limit();
        int capacity = buffer.capacity();
        System.out.println("position:" + position + " limit:" + limit + " capacity:" + capacity);
    }

    private static void test2() throws CharacterCodingException {
        String str = "{\"char\":\"笢\",\"direct\":false,\"double\":1.7114893826238545e+214,\"float\":2.5233746e-18,\"int\":909325612,\"long\":2480473404756814300,\"readOnly\":true,\"short\":8762}";
        byte[] body = str.getBytes(StandardCharsets.UTF_8);
        ByteBuffer byteBuffer = ByteBuffer.wrap(body);

        Charset charset = StandardCharsets.UTF_8;
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = decoder.decode(byteBuffer);
        System.out.println(charBuffer);
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
