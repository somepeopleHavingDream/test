package org.yangxin.test.proxy.jdk;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author yangxin
 * 2021/9/18 15:53
 */
public class Test {

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test2() {
        HelloInterface hello = new Hello();
        ProxyHandler handler = new ProxyHandler(hello);
        System.out.println(Arrays.toString(hello.getClass().getInterfaces()));
        HelloInterface proxyHello = (HelloInterface) Proxy.newProxyInstance(hello.getClass().getClassLoader(),
                hello.getClass().getInterfaces(), handler);
        proxyHello.sayHello();
    }

    /**
     * 静态代理
     */
    private static void test1() {
        HelloProxy helloProxy = new HelloProxy();
        helloProxy.sayHello();
    }
}
