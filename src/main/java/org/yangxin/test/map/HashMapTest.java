package org.yangxin.test.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangxin
 * 2019/12/25 11:58
 */
public class HashMapTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "bb");
        map.put("b", "cc");
        System.out.println(map);

        map.clear();
        System.out.println(map);

        map.put("c", "dd");
        System.out.println(map);
    }
}
