package org.yangxin.test.jvm.classloading;

/**
 * 字段解析
 *
 * @author yangxin
 * 2020/06/23 16:40
 */
public class FieldResolution {

    /**
     * @author yangxin
     * 2020/06/23 16:41
     */
    interface Interface0 {
        int A = 0;
    }

    /**
     * @author yangxin
     * 2020/06/23 16:41
     */
    interface Interface1 extends Interface0 {
        int A = 1;
    }

    /**
     * @author yangxin
     * 2020/06/23 16:42
     */
    interface Interface2 {
        int A = 2;
    }

    static class Parent implements Interface1 {

        public static int A = 3;
    }

    static class Sub extends Parent implements Interface2 {

        // 如果注释了Sub类中的”public static int A = 4;"，接口与父类同时存在字段A，那么编译器将提示“The field Sub.A is ambiguous"，
        // 并且会拒绝编译这段代码
        public static int A = 4;
    }

    public static void main(String[] args) {
        System.out.println(Sub.A);
    }
}
