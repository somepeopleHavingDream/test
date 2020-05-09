package org.yangxin.test.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型通配符
 *
 * @author yangxin
 * 2020/05/09 15:03
 */
public class TypeWildcard {

    public static void test(List<? extends String> list) {
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        // 创建集合对象
        List<String> list = new ArrayList<>();

        list.add("hello");
        list.add("world");
        list.add("java");

        test(list);
    }
}
