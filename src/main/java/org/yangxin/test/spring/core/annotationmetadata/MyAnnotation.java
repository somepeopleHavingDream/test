package org.yangxin.test.spring.core.annotationmetadata;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author yangxin
 * 2023/8/23 22:19
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String value();
}
