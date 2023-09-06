package org.yangxin.test.spring.context.importbeandefinitionregistrar;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author yangxin
 * 2023/9/6 22:12
 */
@Configuration
@Import(MyBeanRegistrar.class)
public class AppConfig {
}
