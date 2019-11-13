package org.yangxin.test.jvm.gc;

/**
 * 此代码演示了两点：
 * 1. 对象可以被GC时自我拯救。
 * 2. 这种自救的机会只有一次，因为一个对象的finalize()方法最多只会被系统自动调用一次
 *
 * @author yangxin
 * 2019/11/13 10:53
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) {
        SAVE_HOOK = new FinalizeEscapeGC();

        // 对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();

        // 因为Finalizer方法优先级很低，暂停0.5秒以等待它
        try {
            Thread.sleep(500);

            if (SAVE_HOOK != null) {
                SAVE_HOOK.isAlive();
            } else {
                System.out.println("no, i am dead:(");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 下面这段代码与上面的完全相同，但是这次自救却失败了
        SAVE_HOOK = null;
        System.gc();
        // 因为Finalizer方法优先级很低，暂停0.5秒以等待它
        try {
            Thread.sleep(500);

            if (SAVE_HOOK != null) {
                SAVE_HOOK.isAlive();
            } else {
                System.out.println("no, i am dead:(");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
