package org.yangxin.test.aboutclass.innerclass;

/**
 * 内部类的位置
 *
 * @author yangxin
 * 2020/08/17 10:17
 */
public class A {

    @SuppressWarnings("InnerClassMayBeStatic")
    class B {

    }

    public void print() {
        class C {

        }
        new C();
    }

    public void print(boolean b) {
        if (b) {
            class D {

            }
            new D();
        }
    }
}
