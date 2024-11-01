package org.yangxin.test.jdk8.system;

import java.util.Date;

/**
 * Java获取系统中默认的编码
 *
 * @author yangxin
 * 2020/09/29 09:17
 */
@SuppressWarnings("unused")
public class SystemTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        System.out.println(new Date(System.currentTimeMillis() - 4 * 60 * 60 * 1000L));
    }

    private static void test1() {
        // 获取系统默认语言
        System.out.println(System.getProperty("user.language"));
        // 操作系统
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.arch"));
        System.out.println(System.getProperty("os.version"));
        // 获取系统属性列表
        System.getProperties().list(System.out);
        // 设置编码
        System.out.println(System.getProperty("user.dir"));
    }
}
