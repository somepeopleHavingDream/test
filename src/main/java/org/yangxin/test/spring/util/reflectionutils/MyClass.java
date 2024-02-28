package org.yangxin.test.spring.util.reflectionutils;

import org.springframework.util.ReflectionUtils;

/**
 * @author yangxin
 * 2023/12/7 22:24
 */
@SuppressWarnings("unused")
public class MyClass {

    public void method1() {
        System.out.println("Executing method1");
    }

    public void method2() {
        System.out.println("Executing method2");
    }

    public static void main(String[] args) {
        // 定义一个回调方法
        ReflectionUtils.MethodCallback methodCallback = method -> {
            System.out.println("Found method: " + method.getName());
            // 在这里可以执行对找到的方法的操作
        };

        // 调用 doWithLocalMethods 方法，传入类和回调方法
        ReflectionUtils.doWithLocalMethods(MyClass.class, methodCallback);
    }
}
