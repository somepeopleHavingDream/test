package org.yangxin.test.jvm.classrunningpractise;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理的简单示例
 *
 * @author yangxin
 * 2020/07/17 15:18
 */
public class DynamicProxyTest {

    /**
     * @author yangxin
     * 2020/07/17 15:19
     */
    interface IHello {

        void sayHello();
    }

    /**
     * @author yangxin
     * 2020/07/17 15:19
     */
    static class Hello implements IHello {

        @Override
        public void sayHello() {
            System.out.println("hello world!");
        }
    }

    /**
     * @author yangxin
     * 2020/07/17 15:20
     */
    static class DynamicProxy implements InvocationHandler {

        Object originalObj;

        Object bind(Object originalObj) {
            this.originalObj = originalObj;
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(),
                    originalObj.getClass().getInterfaces(),
                    this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(originalObj, args);
        }
    }

    public static void main(String[] args) {
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        hello.sayHello();
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }
}
