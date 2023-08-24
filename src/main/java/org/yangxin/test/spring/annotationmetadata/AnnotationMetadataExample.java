package org.yangxin.test.spring.annotationmetadata;

import org.springframework.core.type.StandardAnnotationMetadata;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author yangxin
 * 2023/8/23 22:16
 */
public class AnnotationMetadataExample {

    public static void main(String[] args) {
        // 创建一个类的元数据对象
        StandardAnnotationMetadata metadata = new StandardAnnotationMetadata(MyClass.class);

        // 获取类上的所有注解的类型名称
        Set<String> annotationTypes = metadata.getAnnotationTypes();
        for (String annotationType : annotationTypes) {
            System.out.println("Annotation type: " + annotationType);
        }

        // 判断类上是否存在特定注解
        boolean hasAnnotation = metadata.isAnnotated(MyAnnotation.class.getName());
        System.out.println("Has MyAnnotation? " + hasAnnotation);

        // 获取特定注解的属性值
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(MyAnnotation.class.getName());
        Optional.ofNullable(annotationAttributes)
                .orElse(new HashMap<>())
                .forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
