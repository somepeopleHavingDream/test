package org.yangxin.test.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxin
 * 2020/05/10 17:31
 */
public class Simple {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        for (Integer i : list) {
            list.remove(i);
        }
        System.out.println(list);
    }
}
