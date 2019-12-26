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
        String put1 = map.put("aa", "bb");
        String put2 = map.put("aa", "cc");
        System.out.println(put1 + " " + put2);
    }
}
