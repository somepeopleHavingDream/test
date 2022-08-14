package org.yangxin.test.jdk8.function;

import java.util.function.BiFunction;

/**
 * @author yangxin
 * 2021/9/22 9:51
 */
public class BiFunctionTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        BiFunction<Integer, Integer, Integer> biFunction = Integer::sum;
        System.out.println(biFunction.apply(1, 2));
    }
}
