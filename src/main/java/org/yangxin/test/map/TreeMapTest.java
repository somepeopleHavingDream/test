package org.yangxin.test.map;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author yangxin
 * 2020/05/15 10:54
 */
public class TreeMapTest {

    public static void main(String[] args) {
        Map<Integer, String> map = new TreeMap<>(Comparator.reverseOrder());
        map.put(3, "val");
        map.put(2, "val");
        map.put(1, "val");
        map.put(5, "val");
        map.put(4, "val");
        System.out.println(map);
    }
}
