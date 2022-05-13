package org.yangxin.test.spring.cache;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yangxin
 * 2022/5/13 14:27
 */
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(CachingConfig.class);
        context.refresh();

        Book book = context.getBean(Book.class);
        System.out.println(book.getBook(1));
        System.out.println(book.getBook(1));
        System.out.println(book.getBook(2));

        context.close();
    }
}
