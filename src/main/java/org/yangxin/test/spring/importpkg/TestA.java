package org.yangxin.test.spring.importpkg;

/**
 * @author yangxin
 * 2022/5/13 16:06
 */
public class TestA {

    public void fun(String str) {
        System.out.println(str);
    }

    public void printName() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getClassName());
    }
}
