package org.yangxin.test.reflect.param;

import java.lang.annotation.*;

/**
 * @author yangxin
 * 2021/3/6 下午2:43
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ParamDefaultValue {

    String value();
}

