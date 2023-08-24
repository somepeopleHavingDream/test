package org.yangxin.test.spring.aop.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * @author yangxin
 * 2023/8/20 16:10
 */
public class AopAdvisorExample {

    /**
     * 定义切面逻辑
     */
    private static class LoggingAdvice implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            System.out.println("Before method: " + invocation.getMethod().getName());
            Object result = invocation.proceed();
            System.out.println("After method: " + invocation.getMethod().getName());
            return result;
        }
    }

    /**
     * 定义服务类
     */
    private static class MyService {

        public void doSomething() {
            System.out.println("Service is doing something.");
        }
    }

    public static void main(String[] args) {
        // 创建切面逻辑
        LoggingAdvice loggingAdvice = new LoggingAdvice();

        // 创建切点，定义切入的方法名
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.addMethodName("doSomething");

        // 创建Advisor，将切面逻辑和切点组装
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, loggingAdvice);

        // 创建目标对象
        MyService targetService = new MyService();

        // 使用ProxyFactory创建代理对象
        ProxyFactory proxyFactory = new ProxyFactory(targetService);
        proxyFactory.addAdvisor(advisor);
        MyService proxiedService = (MyService) proxyFactory.getProxy();

        // 使用代理对象调用方法，切面逻辑会被织入
        proxiedService.doSomething();
    }
}
