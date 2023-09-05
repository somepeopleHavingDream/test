package org.yangxin.test.jdk8.annotation.repeatingannotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author yangxin
 * 2023/9/5 21:43
 */
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MyAnnotations.class)
public @interface MyAnnotation {

    String value();
}
