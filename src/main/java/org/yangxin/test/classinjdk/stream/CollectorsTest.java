package org.yangxin.test.classinjdk.stream;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangxin
 * 2021/3/22 18:05
 */
@SuppressWarnings("SimplifyStreamApiCallChains")
public class CollectorsTest {

    public static void toCollection(List<String> list) {
        list.stream()
                .collect(Collectors.toCollection(LinkedList::new))
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("456", "123", "789");
        toCollection(list1);
    }
}
