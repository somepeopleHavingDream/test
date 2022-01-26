package org.yangxin.test.proxy.proxy1;

/**
 * @author yangxin
 * 2021/9/18 15:52
 */
public class HelloProxy implements HelloInterface {

    private final HelloInterface helloInterface = new Hello();

    @Override
    public void sayHello() {
        System.out.println("Before invoke sayHello");
        helloInterface.sayHello();
        System.out.println("After invoke sayHello");
    }
}
