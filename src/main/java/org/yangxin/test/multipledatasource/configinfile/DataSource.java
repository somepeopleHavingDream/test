package org.yangxin.test.multipledatasource.configinfile;

import java.lang.annotation.*;

/**
 * @author yangxin
 * 2021/3/22 9:38
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    /**
     * 默认为ONE，因为后面我们选择配置这个ONE为默认数据库
     *
     * @return 数据库名
     */
    String value() default DataSourceName.ONE;
}
