package org.yangxin.test.aboutclass.initclass;

/**
 * @author yangxin
 * 2020/08/14 17:29
 */
public abstract class People {

    public People() {
        System.out.println("People-1: " + toString());
        initValue();
        System.out.println("People-2: " + toString());
    }

    public abstract void initValue();

    public void show() {
        System.out.println("Show: " + toString());
    }
}
