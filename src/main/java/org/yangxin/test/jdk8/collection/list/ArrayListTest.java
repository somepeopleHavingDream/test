package org.yangxin.test.jdk8.collection.list;

import lombok.Data;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yangxin
 * 2021/8/28 上午11:58
 */
@SuppressWarnings({"Java8CollectionRemoveIf", "AlibabaUndefineMagicConstant", "CommentedOutCode", "unused"})
public class ArrayListTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }

    private static void test5() {
        List<String> list = new ArrayList<>();
        list.add("com.cloud.dev2024.100");
        list.add("7680coin");
        list.add("0.99_7680");
        list.add("4.99_38400");
        list.add("19.99_153600");
        list.add("49.99_384000");
        list.add("99.99_768000");
        list.add("149.99_1152000");
        list.add("499.99_3840000");

        System.out.println(list.contains("4.99_38400"));
    }

    private static void test4() {
        List<Integer> list = Arrays.asList(1, 2);
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        list.forEach(set::remove);

        System.out.println(set);
    }

    private static void test3() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }

        Iterator<String> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            if (i == 3) {
                list.remove(3);
            }
            System.out.println(iterator.next());
            i++;
        }
    }

    private static void test2() {
        List<Row> rowList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Row row = new Row();
            Object[] valueArray = row.valueArray;
            for (int j = 0; j < valueArray.length; j++) {
                if ((j & 1) == 1) {
                    valueArray[j] = "1";
                } else {
                    valueArray[j] = 0;
                }
            }

            rowList.add(row);
        }

        System.out.println(rowList);
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

    @Data
    private static class Row implements Serializable {

        private static final long serialVersionUID = 6537356899740364588L;

        private final Object[] valueArray = new Object[5];
    }
}
