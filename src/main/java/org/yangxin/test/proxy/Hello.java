package org.yangxin.test.proxy;

/**
 * @author yangxin
 * 2021/9/18 15:51
 */
public class Hello implements HelloInterface {

    @Override
    public void sayHello() {
        System.out.println("Hello world!");
    }
}
