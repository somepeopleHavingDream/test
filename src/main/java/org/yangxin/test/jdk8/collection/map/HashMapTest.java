package org.yangxin.test.jdk8.collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author yangxin
 * 2019/12/25 11:58
 */
@SuppressWarnings({"AlibabaCollectionInitShouldAssignCapacity", "AlibabaUndefineMagicConstant"})
public class HashMapTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }

    private static void test4() {
        Map<String, Integer> map = new HashMap<>();
        map.put("name", 1);
        map.merge("name", 1, Integer::sum);
        map.merge("count", 1, Integer::sum);

        System.out.println(map);
    }

    private static void test3() {
        // 创建一个Map
        Map<String, Integer> prices = new HashMap<>();
        prices.put("Shoes", 200);
        prices.put("Bag", 300);
        prices.put("Pant", 150);
        System.out.println(prices);

        // 计算Shirt的值
        Integer shirtPrice = prices.computeIfAbsent("Shirt", key -> 280);
        System.out.println(shirtPrice);

        // 输出更新后的HashMap
        System.out.println(prices);
    }

    private static void test2() {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i + "", i + "");
        }

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            if (i == 3) {
                map.remove(3 + "");
            }

            Map.Entry<String, String> entry = iterator.next();
            System.out.println(entry);

            i++;
        }
    }

    private static void test1() {
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
