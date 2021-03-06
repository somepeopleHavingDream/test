package org.yangxin.test.reflect.cast;

/**
 * @author yangxin
 * 2021/3/6 下午4:20
 */
public class AppleTree implements Tree {

    @Override
    public void print() {
        System.out.println("我是苹果树");
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
