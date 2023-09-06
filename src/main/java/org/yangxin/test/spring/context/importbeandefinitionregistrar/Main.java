package org.yangxin.test.spring.context.importbeandefinitionregistrar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yangxin
 * 2023/9/6 22:13
 */
public class Main {

    public static void main(String[] args) {
        // 创建Spring上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // 获取自定义的bean
        MyCustomBean customBean = context.getBean(MyCustomBean.class);
        // 使用自定义的bean
        customBean.showMessage();
        // 关闭Spring上下文
        context.close();
    }
}
