package org.yangxin.test.spring.boot.conditiononmissingbean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yangxin
 * 2022/5/16 10:15
 */
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfig.class);

        A a = context.getBean(A.class);
        a.print();

        context.close();
    }
}
