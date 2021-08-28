package org.yangxin.test.classinjdk.collection.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author yangxin
 * 2021/8/28 上午11:58
 */
@SuppressWarnings("Java8CollectionRemoveIf")
public class ArrayListTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        List<Integer> list = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 3) {
                iterator.remove();
            }
        }

        System.out.println(list);
    }
}
