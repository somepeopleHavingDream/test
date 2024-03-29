package org.yangxin.test.jdk8.reference.weakreference;

import java.lang.ref.WeakReference;

/**
 * Salad类，继承WeakReference，将Apple（被引用对象）作为弱引用。
 * 注意，到时候回收的是Apple，而不是Salad。
 *
 * @author yangxin
 * 2020/12/12 20:38
 */
public class Salad extends WeakReference<Apple> {

    public Salad(Apple referent) {
        super(referent);
    }
}
