package org.yangxin.test.jdk8.annotation.repeatingannotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author yangxin
 * 2023/9/5 21:44
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotations {

    MyAnnotation[] value();
}
