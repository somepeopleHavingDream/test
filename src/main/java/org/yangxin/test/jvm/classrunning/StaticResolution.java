package org.yangxin.test.jvm.classrunning;

/**
 * 方法静态解析演示
 * 只要能被invokestatic和invokespecial指令调用的方法，都可以在解析阶段确定唯一的调用版本，
 * 符合这个条件的有静态方法、私有方法、实例构造器和父类方法四类，它们在类加载的时候就会把符号引用解析为该方法的直接引用。
 *
 * @author yangxin
 * 2020/07/16 15:55
 */
public class StaticResolution {

    public static void sayHello() {
        System.out.println("Hello World!");
    }

    public static void main(String[] args) {
        StaticResolution.sayHello();
    }
}
