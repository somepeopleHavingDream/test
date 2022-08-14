package org.yangxin.test.jdk8.regex;

import java.util.Arrays;

/**
 * @author yangxin
 * 2021/11/24 17:19
 */
@SuppressWarnings("CommentedOutCode")
public class RegexTest {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
//        test4();
//        test5();
    }

    private static void test5() {
        String str = "aa@aa.net.cn";
        String regex = "\\w+@\\w+\\.(com|net.cn)";
        System.out.println(str.matches(regex));
    }

    private static void test4() {
        String str = "Tom:30|Jerry:20|Bob:25";
        String regex = "\\|";
        String[] array = str.split(regex);
        System.out.println(Arrays.toString(str.split(regex)));
    }

    private static void test3() {
        String str = "12Y34h56dAd7";
        String regex = "[^a-zA-Z]+";
        System.out.println(str.replaceAll(regex, "-"));
    }

    private static void test2() {
        String str = "1234567abc";
        String regex = "\\w{10,}";
        System.out.println(str.matches(regex));
    }

    private static void test1() {
        String str = "1234567";
        String regex = "\\d+";
        System.out.println(str.matches(regex));
    }
}
