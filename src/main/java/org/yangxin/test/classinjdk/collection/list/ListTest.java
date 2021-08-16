package org.yangxin.test.classinjdk.collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author yangxin
 * 2020/03/25 09:01
 */
@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class ListTest {

    public static void main(String[] args) {
//        batch();

//        Object[] objects = new Object[0];
//        System.out.println(objects.length);

        List<Integer> list = new ArrayList<>();
//        list.add(2);
//        list.add(1);
//        list.add(-2);
//        list.add(9);
//        list.add(8);
        Random random = new Random();
//        for (int i = 0; i < 64; i++) {
        for (int i = 0; i < 32; i++) {
            list.add(random.nextInt() % 100 + 1);
        }
        System.out.println(list);
        Collections.sort(list);
    }

    @SuppressWarnings("InstantiationOfUtilityClass")
    private static void batch() {
        // 构建有4万多条数据的集合
        List<Simple> simpleList = new ArrayList<>();
        for (int i = 0; i < 45180; i++) {
            simpleList.add(new Simple());
        }

        // 每次只打印1000条数据
        int count = 0;
        List<Simple> tmpList = new ArrayList<>();
        for (Simple simple : simpleList) {
            tmpList.add(simple);

            // 每次满1000条的时候，做一次操作，然后清空
            if (tmpList.size() >= 1000) {
                System.out.println("tmpList.size: " + tmpList.size());
                tmpList.clear();

                count++;
            }
        }

        if (tmpList.size() != 0) {
            System.out.println("tmpList.size: " + tmpList.size());

            count++;
        }

        System.out.println("simpleList.size: " + simpleList.size());
        System.out.println("count: " + count);
    }
}
