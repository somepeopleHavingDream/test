package org.yangxin.test.reflect;

/**
 * @author yangxin
 * 2020/06/28 11:23
 */
public class ReflectTarget {

    public static void main(String[] args) {
        // 第一种方式获取Class对象
        ReflectTarget reflectTarget = new ReflectTarget();
        Class<? extends ReflectTarget> reflectTargetClass1 = reflectTarget.getClass();
        System.out.println("aClass: " + reflectTargetClass1.getName());

        // 第二种方式获取Class对象
        Class<ReflectTarget> reflectTargetClass2 = ReflectTarget.class;
        System.out.println("reflectTargetClass2: " + reflectTargetClass2.getName());

        // 判断第一种方式获取的class对象和第二种方式获取的是否是同一个
        System.out.println(reflectTargetClass1 == reflectTargetClass2);

        // 第三种方式来获取Class对象
        try {
            Class<?> reflectTargetClass3 = Class.forName("org.yangxin.test.reflect.ReflectTarget");
            System.out.println("reflectTargetClass3: " + reflectTargetClass3.getName());
            System.out.println(reflectTargetClass2 == reflectTargetClass3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
