package org.yangxin.test.jdk8.collection.set;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;

/**
 * @author yangxin
 * 2022/3/24 11:47
 */
public class EnumSetTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        Collection<Session> collection = new HashSet<>();
        collection.add(Session.SPRING);
        collection.add(Session.FALL);

        EnumSet<Session> e = EnumSet.copyOf(collection);
        System.out.println(e);
    }

    private static void test1() {
        // 1 创建一个包含Session（枚举类）里所有枚举值的EnumSet集合
        EnumSet<Session> e1 = EnumSet.allOf(Session.class);
        System.out.println(e1);

        // 2 创建一个空EnumSet
        EnumSet<Session> e2 = EnumSet.noneOf(Session.class);
        System.out.println(e2);

        // 3 add()空EnumSet集合中添加枚举元素
        e2.add(Session.SPRING);
        e2.add(Session.SUMMER);
        System.out.println(e2);

        // 4 以指定枚举值创建EnumSet集合
        EnumSet<Session> e3 = EnumSet.of(Session.SPRING, Session.FALL);
        System.out.println(e3);

        // 5 创建一个包含从from枚举值到to枚举值范围内所有枚举值的EnumSet集合
        EnumSet<Session> e4 = EnumSet.range(Session.SPRING, Session.FALL);
        System.out.println(e4);

        // 6 创建一个其元素类型与指定EnumSet里元素类型相同的EnumSet集合，新EnumSet集合包含原EnumSet集合所不包含的枚举值
        EnumSet<Session> e5 = EnumSet.complementOf(e4);
        System.out.println(e5);
    }

    private enum Session {

        /**
         * 春
         */
        SPRING,

        /**
         * 夏
         */
        SUMMER,

        /**
         * 秋
         */
        FALL,

        /**
         * 冬
         */
        WINTER
    }
}
