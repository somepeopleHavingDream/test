package org.yangxin.test.jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * 方法区OOM
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * @author yangxin
 * 2019/11/12 16:54
 */
public class JavaMethodAreaOOM {
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(objects, args));
            enhancer.create();
        }
    }

    static class OOMObject {

    }
}
