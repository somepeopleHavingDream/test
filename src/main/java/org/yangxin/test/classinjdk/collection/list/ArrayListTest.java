package org.yangxin.test.classinjdk.collection.list;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yangxin
 * 2021/8/28 上午11:58
 */
@SuppressWarnings({"Java8CollectionRemoveIf", "AlibabaUndefineMagicConstant"})
public class ArrayListTest {

    public static void main(String[] args) {
//        test1();
        test2();
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
