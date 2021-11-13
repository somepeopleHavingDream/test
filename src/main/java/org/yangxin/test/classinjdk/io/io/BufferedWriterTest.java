package org.yangxin.test.classinjdk.io.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/**
 * @author yangxin
 * 2021/11/13 下午12:10
 */
public class BufferedWriterTest {

    public static void main(String[] args) throws IOException {
        test1();
    }

    private static void test1() throws IOException {
        FileWriter writer = new FileWriter("other" + File.separator + "big-table.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        for (int i = 0; i < 10000000; i++) {
            String uuid = UUID.randomUUID().toString();
            bufferedWriter.write(i + "," + uuid + "\n");
        }

        bufferedWriter.close();
        writer.close();
        System.out.println("执行完毕");
    }
}
