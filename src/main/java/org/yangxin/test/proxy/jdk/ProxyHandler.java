package org.yangxin.test.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yangxin
 * 2021/9/18 15:48
 */
@SuppressWarnings("SuspiciousInvocationHandlerImplementation")
public class ProxyHandler implements InvocationHandler {

    private final Object object;

    public ProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoke " + method.getName());
        method.invoke(object, args);
        System.out.println("After invoke " + method.getName());

        return null;
    }
}
