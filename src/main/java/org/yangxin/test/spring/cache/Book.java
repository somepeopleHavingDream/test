package org.yangxin.test.spring.cache;

import org.springframework.cache.annotation.Cacheable;

/**
 * @author yangxin
 * 2022/5/13 14:23
 */
public class Book {

    @Cacheable(value = {"sampleCache"})
    public String getBook(int id) {
        System.out.println("Method executed..");

        if (id == 1) {
            return "Book 1";
        } else {
            return "Book 2";
        }
    }
}
