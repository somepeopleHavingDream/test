package org.yangxin.test.jvm.classloading;

/**
 * <clinit>()方法执行顺序
 *
 * @author yangxin
 * 2020/06/23 16:56
 */
public class ClinitOrder {

    /**
     * @author yangxin
     * 2020/06/23 16:58
     */
    static class Parent {

        public static int A = 1;

        static {
            A = 2;
        }
    }

    static class Sub extends Parent {

        public static int B = A;
    }

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }
}
