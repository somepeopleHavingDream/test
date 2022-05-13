package org.yangxin.test.spring.importpkg;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yangxin
 * 2022/5/13 16:08
 */
public class ImportAnnotationTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ImportConfig.class);
        context.refresh();

        TestA testA = context.getBean(TestA.class);
        testA.printName();

        context.close();
    }
}
