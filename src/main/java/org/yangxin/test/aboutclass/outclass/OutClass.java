package org.yangxin.test.aboutclass.outclass;

/**
 * @author yangxin
 * 2020/08/14 17:10
 */
@SuppressWarnings("InstantiationOfUtilityClass")
public class OutClass {

    /**
     * 全局静态变量
     */
    private static final String msg = "i don't like java.";

    /**
     * 创建静态类
     */
    public static class StaticInnerClass {

        public void showMsg() {
            System.out.println("静态内部类展示信息：" + msg);
        }
    }

    /**
     * 创建非静态内部类
     */
    @SuppressWarnings("InnerClassMayBeStatic")
    public class InnerClass {

        public void displayMsg() {
            System.out.println("非静态内部类展示信息：" + msg);
        }
    }

    public static void main(String[] args) {
        // 创建静态内部类实例
        StaticInnerClass staticInnerClass1 = new StaticInnerClass();
        staticInnerClass1.showMsg();

        StaticInnerClass staticInnerClass2 = new StaticInnerClass();
        System.out.println(staticInnerClass1.equals(staticInnerClass2));

        // 创建非静态内部类实例，需要先创建外部类的实例OutClass().new
        InnerClass innerClass = new OutClass().new InnerClass();
        innerClass.displayMsg();
    }
}