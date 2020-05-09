package org.yangxin.test.generic;

/**
 * 泛型方法
 *
 * @author yangxin
 * 2020/05/09 14:50
 */
public class GenericMethod {

    /**
     * 定义泛型方法
     */
    public <T> void show(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        // 创建对象
        GenericMethod genericMethod = new GenericMethod();

        // 调用方法，传入的参数是什么类型，返回值就是什么类型
        genericMethod.show("hello");
        genericMethod.show(12);
        genericMethod.show(12.5);
    }
}
