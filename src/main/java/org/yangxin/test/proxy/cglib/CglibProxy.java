package org.yangxin.test.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理，实现MethodInterceptor接口
 *
 * Jdk动态代理和cglib字节码生成的区别？
 * （1）jdk动态代理只能对实现了接口的类生成代理，而不能针对类
 * （2）cglib是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法，因为是继承，所以该类或方法最好不要声明成final
 *
 * @author yangxin
 * 2022/1/26 18:07
 */
public class CglibProxy implements MethodInterceptor {

    /**
     * 需要代理的目标对象
     */
    private Object target;

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib动态代理，监听开始！");
        Object invoke = method.invoke(target, args);
        System.out.println("cglib动态代理，监听结束！");

        return invoke;
    }

    /**
     * 定义获取代理对象方法
     *
     * @param objectTarget 对象目标
     * @return 对象
     */
    public Object getCglibProxy(Object objectTarget) {
        // 为目标对象target赋值
        this.target = objectTarget;

        Enhancer enhancer = new Enhancer();
        // 设置父类，因为cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        // 设置回调
        enhancer.setCallback(this);

        // 创建并返回代理对象
        return enhancer.create();
    }

    public static void main(String[] args) {
        // 实例化CglibProxy对象
        CglibProxy proxy = new CglibProxy();
        // 获取代理对象
        UserManager userManager = (UserManager) proxy.getCglibProxy(new UserManagerImpl());
        userManager.deleteUser("admin");
    }
}
