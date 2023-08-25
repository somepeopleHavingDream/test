package org.yangxin.test.spring.core.annotation.annotationmetadata;

/**
 * @author yangxin
 * 2023/8/23 22:20
 */
@SuppressWarnings("unused")
@MyAnnotation(value = "Hello, Annotation!")
public class MyClass {

    public void someMethod() {
        System.out.println("someMethod");
    }
}
