package org.yangxin.test.classinjdk.basictype;

import java.util.StringJoiner;

/**
 * @author yangxin
 * 2021/9/22 14:48
 */
public class StringJoinerTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        StringJoiner joiner = new StringJoiner(":", "[", "]");
        joiner.add("George")
                .add("Sally")
                .add("Fred");
        System.out.println(joiner);
    }
}
