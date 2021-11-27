package org.yangxin.test.spring.stringutils;

import org.springframework.util.StringUtils;

/**
 * @author yangxin
 * 2021/11/27 下午9:43
 */
public class StringUtilsTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        String path = "/home/yangxin/IdeaProjects/spring-framework-5.2.9.RELEASE/spring-demo/src/main/resources/spring/spring-config.xml";
        System.out.println(StringUtils.cleanPath(path));
    }
}
