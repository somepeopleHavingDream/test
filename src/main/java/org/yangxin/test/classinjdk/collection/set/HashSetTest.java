package org.yangxin.test.classinjdk.collection.set;

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
        test1();
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
