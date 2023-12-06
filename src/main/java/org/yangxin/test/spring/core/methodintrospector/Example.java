package org.yangxin.test.spring.core.methodintrospector;

import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @author yangxin
 * 2023/12/6 22:21
 */
public class Example {

    public static void main(String[] args) {
        Class<?> targetClass = YourClass.class;

        // 使用 MethodIntrospector 选择所有标记有特定注解的方法
        Map<Method, YourAnnotation> annotatedMethods = MethodIntrospector.selectMethods(targetClass,
                (MethodIntrospector.MetadataLookup<YourAnnotation>) method -> {
                    YourAnnotation annotation = AnnotatedElementUtils
                            .findMergedAnnotation(method, YourAnnotation.class);
                    return Objects.nonNull(annotation)
                            ? annotation
                            : AnnotationUtils.findAnnotation(method, YourAnnotation.class);
                });

        // 处理选定的方法和它们的元数据
        for (Map.Entry<Method, YourAnnotation> entry : annotatedMethods.entrySet()) {
            Method method = entry.getKey();
            YourAnnotation annotation = entry.getValue();

            // 在这里处理选定的方法和它们的元数据
            System.out.println(method.getName());
            System.out.println(annotation.value());
        }
    }

    /**
     * 示例注解
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface YourAnnotation {

        String value();
    }

    /**
     * 示例类
     */
    @SuppressWarnings("unused")
    static class YourClass {

        @YourAnnotation("method1")
        public void method1() {
        }

        @YourAnnotation("method2")
        public void method2() {
        }

        public void nonAnnotatedMethod() {
        }
    }
}
