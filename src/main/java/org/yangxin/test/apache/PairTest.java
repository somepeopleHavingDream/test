package org.yangxin.test.apache;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

/**
 * @author yangxin
 * 2023/4/21 10:27
 */
public class PairTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        Triple<String, Integer, Double> triple = Triple.of("Hello", 1, 3.14);
        System.out.println(triple.getLeft());
        System.out.println(triple.getMiddle());
        System.out.println(triple.getRight());
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
