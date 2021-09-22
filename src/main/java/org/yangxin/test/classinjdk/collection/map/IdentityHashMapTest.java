package org.yangxin.test.classinjdk.collection.map;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author yangxin
 * 2021/9/22 10:13
 */
public class IdentityHashMapTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Map<String, String> map = new IdentityHashMap<>();
        map.put(new String("a"), "1");
        map.put(new String("a"), "2");
        map.put(new String("a"), "3");
        System.out.println(map.size());
        System.out.println(map.get("a"));
        System.out.println(map.get(new String("a")));
    }
}
