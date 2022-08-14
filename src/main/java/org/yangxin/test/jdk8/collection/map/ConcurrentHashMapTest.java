package org.yangxin.test.jdk8.collection.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangxin
 * 2021/11/30 下午11:16
 */
@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "ConstantConditions"})
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Map<String, String> map = new ConcurrentHashMap<>(256);
        System.out.println(map.size());
    }
}
