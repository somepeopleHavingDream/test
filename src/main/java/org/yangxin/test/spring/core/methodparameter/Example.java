package org.yangxin.test.spring.core.methodparameter;

import org.springframework.core.MethodParameter;

import java.lang.reflect.Constructor;

/**
 * @author yangxin
 * 2023/9/27 22:35
 */
@SuppressWarnings("unused")
public class Example {

    public Example(String name, int age) {
    }

    public void someMethod(String message, double value) {
    }

    public static void main(String[] args) throws NoSuchMethodException {
        // 使用反射获取构造函数
        Constructor<Example> constructor = Example.class.getConstructor(String.class, int.class);

        // 使用forExecutable创建MethodParameter对象
        MethodParameter parameter = MethodParameter.forExecutable(constructor, 0);

        // 访问参数的属性
        System.out.println(parameter.getParameterName());
        System.out.println(parameter.getParameterType());
    }
}
