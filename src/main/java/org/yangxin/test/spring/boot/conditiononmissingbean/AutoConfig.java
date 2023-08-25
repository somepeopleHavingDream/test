package org.yangxin.test.spring.boot.conditiononmissingbean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author yangxin
 * 2022/5/16 10:10
 */
@Component
public class AutoConfig {

    @Bean
    public A a1() {
        System.out.println("a1");
        return new A();
    }

    @Bean
    @ConditionalOnMissingBean(A.class)
//    @Primary
    public A a2() {
        System.out.println("a2");
        return new A();
    }
}