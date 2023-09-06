package org.yangxin.test.spring.context.importbeandefinitionregistrar;

/**
 * @author yangxin
 * 2023/9/6 22:03
 */
public class MyCustomBean {

    private final String message;

    public MyCustomBean(String message) {
        this.message = message;
    }

    public void showMessage() {
        System.out.println("Message from MyCustomBean: " + message);
    }
}
