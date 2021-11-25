package org.yangxin.test.classinjdk.collection.collections;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yangxin
 * 2021/11/25 11:29
 */
@SuppressWarnings("ConstantConditions")
public class CollectionsTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Set<String> set = new HashSet<>();

        set.add("welcome");
        set.add("to");
        set.add("tp");

        System.out.println(set);

        Set<String> unmodifiableSet = Collections.unmodifiableSet(set);
        unmodifiableSet.add("hello");
    }
}
