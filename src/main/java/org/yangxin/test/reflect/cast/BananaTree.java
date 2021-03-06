package org.yangxin.test.reflect.cast;

/**
 * @author yangxin
 * 2021/3/6 下午4:21
 */
public class BananaTree implements Tree {

    @Override
    public void print() {
        System.out.println("我是香蕉树");
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
