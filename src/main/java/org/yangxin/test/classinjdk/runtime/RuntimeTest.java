package org.yangxin.test.classinjdk.runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author yangxin
 * 2021/11/15 11:34
 */
public class RuntimeTest {

    public static void main(String[] args) throws IOException {
//        test1();
        test2();
    }

    private static void test2() throws IOException {
        Process process = Runtime.getRuntime().exec("java -version");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    /**
     * shutdown在程序正常结束的时候会调用
     */
    private static void test1() {
        System.out.println("application start");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("hook1")));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("hook2")));
        System.out.println("application end");
    }
}