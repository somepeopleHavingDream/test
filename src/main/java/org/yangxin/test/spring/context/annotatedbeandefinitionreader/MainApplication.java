package org.yangxin.test.spring.context.annotatedbeandefinitionreader;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author yangxin
 * 2023/9/21 22:23
 */
@ComponentScan(basePackages = "org.yangxin.test.spring.context.annotatedbeandefinitionreader")
public class MainApplication {

    public static void main(String[] args) {
        // 创建一个AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 获取DefaultListableBeanFactory
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();

        // 创建AnnotatedBeanDefinitionReader
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);

        // 注册配置类
        reader.register(MainApplication.class);

        // 启动应用上下文
        context.refresh();

        // 获取Spring Bean
        MyComponent myComponent = context.getBean(MyComponent.class);

        // 使用Spring Bean
        myComponent.sayHello();

        // 关闭应用上下文
        context.close();
    }
}

@Component
class MyComponent {

    public void sayHello() {
        System.out.println("Hello from MyComponent!");
    }
}
