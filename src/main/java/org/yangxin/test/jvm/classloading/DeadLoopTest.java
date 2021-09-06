package org.yangxin.test.jvm.classloading;

/**
 * 字段解析
 * 如果一个类的<clinit>()方法中有耗时很长的操作，那就可能造成多个进程阻塞，在实际应用中这种阻塞往往是很隐蔽的
 *
 * @author yangxin
 * 2020/06/23 17:03
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class DeadLoopTest {

    /**
     * @author yangxin
     * 2020/06/23 17:04
     */
    @SuppressWarnings({"ConstantConditions", "InfiniteLoopStatement", "StatementWithEmptyBody"})
    static class DeadLoopClass {

        // 虚拟机会保证一个类的<clinit>()方法在多线程环境中被正确地加锁和同步，如果多个线程同时去初始化一个类，
        // 那么只会有一个线程去执行这个类的<clint>()方法，其他线程都需要阻塞等待，直到活动线程执行<clinit>()方法完毕。
        static {
            // 如果不加上这个if语句，编译器将提示“Initializer doest not complete normally"并拒绝编译
            if (true) {
                System.out.println(Thread.currentThread() + " init DeadLoopClass");
                while (true) {

                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable script = () -> {
            System.out.println(Thread.currentThread() + " start");
            DeadLoopClass deadLoopClass = new DeadLoopClass();
            System.out.println(Thread.currentThread() + " run over");
        };

        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);
        thread1.start();
        thread2.start();
    }
}