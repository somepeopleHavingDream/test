package org.yangxin.test.generic;

/**
 * 子类明确泛型类的类型参数变量
 * 子类不明确泛型类的类型参数变量：
 *  实现类也要定义出<T>类型的
 *
 * @param <T>
 * @author yangxin
 * 2020/05/09 14:55
 */
public class GenericInterImpl<T> implements GenericInter<T> {
//public class GenericInterImpl<T> implements GenericInter<String> {

//    @Override
//    public void show(String s) {
//        System.out.println(s);
//    }

    /**
     * 第二种情况测试
     */
    @Override
    public void show(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        // 第二种情况测试
        GenericInter<String> genericInter = new GenericInterImpl<>();
        genericInter.show("100");
    }
}
