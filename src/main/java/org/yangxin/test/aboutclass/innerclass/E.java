package org.yangxin.test.aboutclass.innerclass;

/**
 * 静态内部类/静态嵌套类
 *
 * @author yangxin
 * 2020/08/17 10:49
 */
public class E {

    private void show() {
        new A();
    }

    /**
     * 类A使用了static，所有是静态嵌套类，在这里使用private修饰；
     * 那么该类只能在E类中进行实例化；无法在其他文件中实例化
     *
     * 静态嵌套类A对其包含类E完全透明，但E并不对A透明
     *
     * @author yangxin
     * 2020/08/17 10:52
     */
    private static class A {

    }
}
