package org.yangxin.test.aboutclass.innerclass;

/**
 * 内部类的位置
 *
 * @author yangxin
 * 2020/08/17 10:21
 */
public class B {

    public void show() {
        class Man implements AInterface {

            @Override
            public void show() {

            }
        }

        Man man = new Man();
        man.show();
    }
}
