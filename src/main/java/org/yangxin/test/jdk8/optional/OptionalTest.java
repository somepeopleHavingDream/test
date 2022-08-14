package org.yangxin.test.jdk8.optional;

import java.util.Optional;

/**
 * @author yangxin
 * 2022/2/11 11:35
 */
@SuppressWarnings("ConstantConditions")
public class OptionalTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Integer a = null;
        Integer b = -2;
        System.out.println(Optional.ofNullable(a).filter(e -> e > 0).orElse(0));
        System.out.println(Optional.ofNullable(b).filter(e -> e > 0).orElse(0));
    }
}
