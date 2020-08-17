package org.yangxin.test.aboutclass.innerclass;

/**
 * 初始化的区别
 *
 * @author yangxin
 * 2020/08/17 11:25
 */
public class H {

    int a = 1;

    /**
     * @author yangxin
     * 2020/08/17 11:25
     */
    public class A {

        public void show () {
            System.out.println("a: " + a);
        }
    }

    public static class B {

        public void show(H h) {
            System.out.println("a: " + h.a);
        }
    }

    public static void main(String[] args) {
        H h = new H();
//        new A();
        A a1 = h.new A();
        B b = new B();
//        h.new B();
        B b1 = new B();
    }
}
