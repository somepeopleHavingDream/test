package org.yangxin.test.spring.context.importpkg;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author yangxin
 * 2022/5/13 16:07
 */
@Import({TestA.class})
@Configuration
public class ImportConfig {
}
