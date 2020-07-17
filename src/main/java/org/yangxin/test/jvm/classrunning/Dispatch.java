package org.yangxin.test.jvm.classrunning;

/**
 * 单分派、多分派演示
 *
 * @author yangxin
 * 2020/07/17 13:59
 */
public class Dispatch {

    /**
     * @author yangxin
     * 2020/07/17 14:03
     */
    static class QQ {
    }

    /**
     * @author yangxin
     * 2020/07/17 14:03
     */
    static class _360 {
    }

    /**
     * @author yangxin
     * 2020/07/17 14:02
     */
    public static class Father {

        public void hardChoice(QQ arg) {
            System.out.println("father choose qq");
        }

        public void hardChoice(_360 arg) {
            System.out.println("father choose 360");
        }
    }

    /**
     * @author yangxin
     * 2020/07/17 14:03
     */
    public static class Son extends Father {

        public void hardChoice(QQ arg) {
            System.out.println("son choose qq");
        }

        public void hardChoice(_360 arg) {
            System.out.println("son choose 360");
        }
    }

    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new _360());
        son.hardChoice(new QQ());
    }
}
