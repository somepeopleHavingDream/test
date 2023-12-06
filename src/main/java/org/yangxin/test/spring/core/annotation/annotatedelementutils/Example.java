package org.yangxin.test.spring.core.annotation.annotatedelementutils;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;
import java.util.Optional;

/**
 * @author yangxin
 * 2023/12/6 22:39
 */
@SuppressWarnings("unused")
public class Example {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        // 使用 findMergedAnnotation 查找合并注解
        MyAnnotation mergedAnnotation = AnnotatedElementUtils.findMergedAnnotation(MyClass.class, MyAnnotation.class);
        System.out.println(Optional.ofNullable(mergedAnnotation).map(MyAnnotation::value).orElse(null));

        // 使用 findAnnotation 查找注解
        MyAnnotation singleAnnotation = AnnotationUtils.findAnnotation(MyClass.class, MyAnnotation.class);
        System.out.println(Optional.ofNullable(singleAnnotation).map(MyAnnotation::value).orElse(null));
    }

    private static void test1() {
        // 获取类上的合并注解
        MyAnnotation myAnnotation = AnnotatedElementUtils.findMergedAnnotation(MyClass.class, MyAnnotation.class);

        if (Objects.nonNull(myAnnotation)) {
            System.out.println(myAnnotation.value());
        } else {
            System.out.println("Annotation not found");
        }
    }

    /**
     * 示例注解
     */
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MyAnnotation {

        String value() default "default value";
    }

    /**
     * 示例类
     */
    @MyAnnotation(value = "class annotation")
    static class MyBaseClass {
    }

    @MyAnnotation(value = "subclass annotation")
    static class MyClass extends MyBaseClass {
    }
}
