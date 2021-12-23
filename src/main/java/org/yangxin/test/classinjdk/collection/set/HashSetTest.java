package org.yangxin.test.classinjdk.collection.set;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author yangxin
 * 2021/8/27 17:50
 */
@SuppressWarnings({"AlibabaRemoveCommentedCode", "CommentedOutCode"})
public class HashSetTest {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    private static void test3() {
        Set<Integer> set = new HashSet<>();
        set.add(null);
        set.add(1);
        System.out.println(set);
    }

    private static void test2() {
        Set<String> set = Stream.of("A", "B").collect(Collectors.toSet());
        System.out.println(set.add("A"));
    }

    private static void test1() {
        Set<String> set1 = Stream.of("a", "b", "c").collect(Collectors.toSet());
        Set<String> set2 = Stream.of("a", "b").collect(Collectors.toSet());

//        set1.retainAll(set2);
//        set1.removeAll(set2);
        set2.removeAll(set1);
        System.out.println(set2);
    }
}
