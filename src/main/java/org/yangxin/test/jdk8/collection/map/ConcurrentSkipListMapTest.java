package org.yangxin.test.jdk8.collection.map;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author yangxin
 * 2022/7/21 21:42
 */
public class ConcurrentSkipListMapTest {

    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
        map.put(4, "4");
        map.put(5, "5");
        map.put(1, "1");
        map.put(6, "6");
        map.put(2, "2");

        System.out.println(map.keySet());
        System.out.println(map);
        System.out.println(map.descendingKeySet());
        System.out.println(map.descendingMap());
    }
}
