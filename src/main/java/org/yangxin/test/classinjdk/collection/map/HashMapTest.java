package org.yangxin.test.classinjdk.collection.map;

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
        test2();
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
