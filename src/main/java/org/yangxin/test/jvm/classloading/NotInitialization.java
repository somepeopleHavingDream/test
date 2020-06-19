package org.yangxin.test.jvm.classloading;

/**
 * 非主动使用类字段演示
 * -XX:+TraceClassLoading
 *
 * 被动演示类字段演示二：
 * 通过数组定义来引用类，不会触发此类的初始化
 *
 * @author yangxin
 * 2020/06/18 17:53
 */
public class NotInitialization {

    public static void main(String[] args) {
//        System.out.println(SubClass.value);
//        SuperClass[] superClasses = new SuperClass[10];
        System.out.println(ConstClass.HELLO_WORLD);
    }
}
