package org.yangxin.test.jvm.jvisualvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author yangxin
 * 2022/8/21 17:07
 */
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class BtraceTest {

    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) throws IOException {
        BtraceTest test = new BtraceTest();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            reader.readLine();

            int a = (int) Math.round(Math.random() * 1000);
            int b = (int) Math.round(Math.random() * 1000);

            System.out.println(test.add(a, b));
        }
    }
}
