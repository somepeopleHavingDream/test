package org.yangxin.test.spring.core.methodparameter;

import org.springframework.core.MethodParameter;

/**
 * @author yangxin
 * 2023/9/17 17:38
 */
@SuppressWarnings("unused")
public class MethodParameterExample {

    public void myMethod(String name, int age) throws NoSuchMethodException {
        // 创建MethodParameter对象
        MethodParameter nameParameter
                = new MethodParameter(getClass().getMethod("myMethod", String.class, int.class), 0);
        MethodParameter ageParameter
                = new MethodParameter(getClass().getMethod("myMethod", String.class, int.class), 1);

        // 获取参数的名称和类型
        String paramName = nameParameter.getParameterName();
        Class<?> paramType = ageParameter.getParameterType();

        System.out.println(paramName);
        System.out.println(paramType);
    }

    public static void main(String[] args) throws NoSuchMethodException {
        MethodParameterExample example = new MethodParameterExample();
        example.myMethod("Alice", 30);
    }
}
