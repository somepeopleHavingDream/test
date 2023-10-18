package org.yangxin.test.spring.core.instancecomparator;

import org.springframework.util.comparator.InstanceComparator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxin
 * 2023/10/16 21:11
 */
public class InstanceComparatorExample {

    public static void main(String[] args) {
        List<Object> objects = new ArrayList<>();
        objects.add(new MyClassB());
        objects.add(new MyClassA());
        objects.add(new MyClassC());

        InstanceComparator<Object> comparator = new InstanceComparator<>();
        objects.sort(comparator);

        for (Object object : objects) {
            System.out.println(object.getClass().getSimpleName());
        }
    }
}

class MyClassA {

}

class MyClassB {

}

class MyClassC {

}