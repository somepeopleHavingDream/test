package org.yangxin.test.jdk8.annotation.repeatingannotation;

/**
 * @author yangxin
 * 2023/9/5 21:45
 */
@MyAnnotation("Annotation 1")
@MyAnnotation("Annotation 2")
public class MyClass {

    public static void main(String[] args) {
        // 获取注解值
        MyAnnotation[] annotations = MyClass.class.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation annotation : annotations) {
            System.out.println(annotation.value());
        }
    }
}
