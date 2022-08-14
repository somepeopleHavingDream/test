package org.yangxin.test.jdk8.collection.map;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author yangxin
 * 2020/05/15 10:54
 */
@SuppressWarnings("AlibabaRemoveCommentedCode")
public class TreeMapTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        SortedMap<Integer, String> map = new TreeMap<>();
//        SortedMap<Integer, String> map = new TreeMap<>(Comparator.reverseOrder());
        map.put(3, "val");
        map.put(2, "val");
        map.put(1, "val");
        map.put(5, "val");
        map.put(4, "val");

        System.out.println(map);
        System.out.println(map.tailMap(3));
        System.out.println(map.headMap(3));
        System.out.println(map.firstKey());
        System.out.println(map.lastKey());
    }
}
