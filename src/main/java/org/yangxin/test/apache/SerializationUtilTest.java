package org.yangxin.test.apache;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.SerializationUtils;

import java.io.Serializable;

/**
 * @author yangxin
 * 2021/11/1 11:03
 */
public class SerializationUtilTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Person p1 = new Person("letiantian", 18);
        Person p2 = (Person) SerializationUtils.clone(p1);

        System.out.println(p1 == p2);
        System.out.println(p1.name == p2.name);
        System.out.println(p2.name);
        System.out.println(p2.age);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    private static class Person implements Serializable {

        private static final long serialVersionUID = -863210317302166867L;

        private String name;

        private Integer age;
    }
}
