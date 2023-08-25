package org.yangxin.test.spring.core.annotation.annotationattributes;

import org.springframework.core.annotation.AnnotationAttributes;

/**
 * @author yangxin
 * 2023/8/25 10:49
 */
public class AnnotationAttributesExample {

    public static void main(String[] args) {
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
