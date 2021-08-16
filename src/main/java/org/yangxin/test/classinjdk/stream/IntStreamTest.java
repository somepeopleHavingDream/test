package org.yangxin.test.classinjdk.stream;

import java.util.stream.IntStream;

/**
 * @author yangxin
 * 2020/02/15 21:20
 */
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class IntStreamTest {

    public static void main(String[] args) {
        IntStream.range(1, 10).forEach(System.out::println);

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
