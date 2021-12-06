package org.yangxin.test.spring.filterregistrationbean;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangxin
 * 2021/12/6 11:45
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<Test1Filter> registerTest1() {
        /*
            通过FilterRegistrationBean实例设值优先级可以生效，通过@WebFilter无效
         */
        FilterRegistrationBean<Test1Filter> bean = new FilterRegistrationBean<>();
        // 注册自定义过滤器
        bean.setFilter(new Test1Filter());
        // 过滤器名称
        bean.setName("filter1");
        // 过滤所有路径
        bean.addUrlPatterns("/*");
        // 优先级，最顶级
        bean.setOrder(1);

        return bean;
    }

    @Bean
    public FilterRegistrationBean<Test2Filter> registerTest2() {
        /*
            通过FilterRegistrationBean实例设值优先级可以生效，通过@WebFilter无效
         */
        FilterRegistrationBean<Test2Filter> bean = new FilterRegistrationBean<>();
        // 注册自定义过滤器
        bean.setFilter(new Test2Filter());
        // 过滤器名称
        bean.setName("filter2");
        // 过滤所有路径
        bean.addUrlPatterns("/test/*");
        // 优先级，越低越优先
        bean.setOrder(6);

        return bean;
    }
}
