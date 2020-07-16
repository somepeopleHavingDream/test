package org.yangxin.test.jvm.classrunning;

/**
 * 方法动态分派演示
 *
 * @author yangxin
 * 2020/07/16 16:22
 */
public class DynamicDispatch {

    /**
     * @author yangxin
     * 2020/07/16 16:23
     */
    static abstract class Human {

        protected abstract void sayHello();
    }

    /**
     * @author yangxin
     * 2020/07/16 16:24
     */
    static class Man extends Human {

        @Override
        protected void sayHello() {
            System.out.println("man say hello.");
        }
    }

    /**
     * @author yangxin
     * 2020/07/16 16:25
     */
    static class Women extends Human {

        @Override
        protected void sayHello() {
            System.out.println("women say hello.");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Women();
        man.sayHello();
        women.sayHello();

        man = new Women();
        man.sayHello();
    }
}
