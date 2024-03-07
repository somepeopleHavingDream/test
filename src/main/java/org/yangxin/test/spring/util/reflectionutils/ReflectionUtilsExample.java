package org.yangxin.test.spring.util.reflectionutils;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author yangxin
 * 2023/12/7 22:24
 */
@SuppressWarnings({"unused", "CallToPrintStackTrace"})
public class ReflectionUtilsExample {

    public void method1() {
        System.out.println("Executing method1");
    }

    public void method2() {
        System.out.println("Executing method2");
    }

    public static void main(String[] args) throws NoSuchMethodException {
//        testDoWithLocalMethods();
        testMakeAccessible();
    }

    private static void testMakeAccessible() {
        // 创建一个 Method 对象（示例中使用String类的equals方法）
        Method method;
        try {
            method = String.class.getDeclaredMethod("equals", Object.class);

            // 将方法设置为可访问状态
            ReflectionUtils.makeAccessible(method);

            // 尝试调用方法
            String str = "test";
            boolean result = (boolean) method.invoke(str, "test");

            // 输出方法调用结果
            System.out.println("Method invocation result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testDoWithLocalMethods() {
        // 定义一个回调方法
        ReflectionUtils.MethodCallback methodCallback = method -> {
            System.out.println("Found method: " + method.getName());
            // 在这里可以执行对找到的方法的操作
        };

        // 调用 doWithLocalMethods 方法，传入类和回调方法
        ReflectionUtils.doWithLocalMethods(ReflectionUtilsExample.class, methodCallback);
    }
}
