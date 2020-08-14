package org.yangxin.test.aboutclass.initclass;

/**
 * 1. 在类属性初始化的时候，其直接赋值的默认值将会在父类的构造函数之后进行。
 * 2. 在父类操作的时候，其子类的属性并不具备值：name属性为null
 * 3. 在父类中可以通过方法对实现类的属性进行赋值操作，但是当其实现类中默认有对值进行赋值的话，那么将会自动进行二次赋值。
 *
 * @author yangxin
 * 2020/08/14 17:32
 */
public class MainClass {

    public static void main(String[] args) {
        Man man = new Man();
        man.show();
    }
}
