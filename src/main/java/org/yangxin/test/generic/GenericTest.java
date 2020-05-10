package org.yangxin.test.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型使用用例
 *
 * @author yangxin
 * 2020/05/09 14:37
 */
public class GenericTest {

    public static void main(String[] args) {
        // 创建集合对象
        List<String> list = new ArrayList<>();

        list.add("hello");
        list.add("world");
        list.add("java");

        // 遍历，由于明确了类型，我们可以增强for
        list.forEach(System.out::println);
    }
}
