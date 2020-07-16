package org.yangxin.test.jvm.classrunning;

/**
 * 方法静态分派演示
 *
 * @author yangxin
 * 2020/07/16 16:03
 */
public class StaticDispatch {

    /**
     * @author yangxin
     * 2020/07/16 16:04
     */
    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Women extends Human {

    }

    public void sayHello(Human guy) {
        System.out.println("hello, guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello, gentleman!");
    }

    public void sayHello(Women guy) {
        System.out.println("hello, lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Women();
        StaticDispatch staticDispatch = new StaticDispatch();
        staticDispatch.sayHello(man);
        staticDispatch.sayHello(woman);
    }
}
