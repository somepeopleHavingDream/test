package org.yangxin.test.jvm.classloading;

/**
 * @author yangxin
 * 2020/06/18 17:52
 */
public class SubClass extends SuperClass {

    static {
        System.out.println("SubClass init!");
    }
}
