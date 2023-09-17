package org.yangxin.test.spring.bean.dependencydescriptor;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangxin
 * 2023/9/17 17:10
 */
@SuppressWarnings("ClassEscapesDefinedScope")
@Configuration
public class DependencyDescriptorExample {

    @Bean
    public MyBean myBean(AnotherBean anotherBean) {
        return new MyBean(anotherBean);
    }

    @Bean
    public AnotherBean anotherBean() {
        return new AnotherBean();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(DependencyDescriptorExample.class);

        DependencyDescriptor dependencyDescriptor = new DependencyDescriptor(MyBean.class.getDeclaredFields()[0],
                true);
        Object resolvedDependency = context.getAutowireCapableBeanFactory().resolveDependency(dependencyDescriptor,
                "myBean");

        System.out.println(resolvedDependency);
    }
}

@Data
class MyBean {

    private final AnotherBean anotherBean;

    @Autowired
    MyBean(AnotherBean anotherBean) {
        this.anotherBean = anotherBean;
    }
}

@Data
class AnotherBean {
}
