package org.yangxin.test.classinjdk.io.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author yangxin
 * 2020/05/27 10:08
 */
public class FileInputStreamTest {

    public static void main(String[] args) {
//        method1();
        method2();
    }

    private static void method2() {
//        RandomAccessFile randomAccessFile;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("/home/yangxin/IdeaProjects/test/src/main/resources/application.yml", "rw")) {
//            randomAccessFile = new RandomAccessFile("/home/yangxin/IdeaProjects/test/src/main/resources/application.yml", "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int bytesRead = fileChannel.read(buffer);
            System.out.println(bytesRead);

            while (bytesRead != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.compact();
                bytesRead = fileChannel.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void method1() {
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream("/home/yangxin/IdeaProjects/test/src/main/resources/application.yml"))) {
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print((char) buffer[i]);
                }
                bytesRead = inputStream.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
