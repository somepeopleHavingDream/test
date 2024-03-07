package org.yangxin.test.spring.core.annotation.annotationattributes;

import org.springframework.core.annotation.AnnotationAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangxin
 * 2023/8/25 10:49
 */
@SuppressWarnings("unused")
public class AnnotationAttributesExample {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        // 创建一个包含注解属性及其值的Map对象
        Map<String, Object> attributeMap = new HashMap<>();
        attributeMap.put("value", "exampleValue");
        attributeMap.put("enabled", true);
        attributeMap.put("priority", 10);

        // 调用fromMap方法将Map对象转换为AnnotationAttributes对象
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(attributeMap);

        // 获取转换后的AnnotationAttributes对象中的属性值
        String value = annotationAttributes.getString("value");
        boolean enabled = annotationAttributes.getBoolean("enabled");
        int priority = annotationAttributes.getNumber("priority");

        // 打印属性值
        System.out.println("value: " + value);
        System.out.println("enabled: " + enabled);
        System.out.println("priority: " + priority);
    }

    private static void test1() {
        // 创建一个AnnotationAttributes实例
        AnnotationAttributes attributes = new AnnotationAttributes();

        // 设置注解属性值
        attributes.put("value", "Hello, Annotation!");

        // 获取注解属性值
        String value = attributes.getString("value");
        System.out.println("Annotation value: " + value);

        // 设置另一个注解属性值
        attributes.put("count", 10);

        // 获取注解属性值（类型转换）
        int count = attributes.getNumber("count");
        System.out.println("Annotation count: " + count);
    }
}
