package org.yangxin.test.spring.context.importbeandefinitionregistrar;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;

/**
 * @author yangxin
 * 2023/9/6 22:05
 */
public class MyBeanRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(@NonNull AnnotationMetadata importingClassMetadata,
                                        @NonNull BeanDefinitionRegistry registry) {
        // 在这里注册自定义的bean
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MyCustomBean.class);
        builder.addConstructorArgValue("Hello, Custom Bean!");

        registry.registerBeanDefinition("myCustomBean", builder.getBeanDefinition());
    }
}
