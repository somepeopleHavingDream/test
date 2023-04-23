package org.yangxin.test.apache;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author yangxin
 * 2023/4/21 10:27
 */
public class PairTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Pair<String, Integer> pair = Pair.of("Hello", 1);
        System.out.println(pair.getLeft());
        System.out.println(pair.getRight());
        System.out.println(pair);
        System.out.println(pair.getKey());
        System.out.println(pair.getValue());
    }
}
