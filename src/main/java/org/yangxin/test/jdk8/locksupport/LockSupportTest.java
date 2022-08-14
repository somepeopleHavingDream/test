package org.yangxin.test.jdk8.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @author yangxin
 * 1/14/21 11:19 PM
 */
public class LockSupportTest {

    public static final Object INSTANCE = new Object();

    /**
     * @author yangxin
     * 2020/11/14 23:20
     */
    public static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (INSTANCE) {
                System.out.println("in " + getName());

                LockSupport.park();
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("被中断了……");
                }
                System.out.println("继续执行……");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectThread t1 = new ChangeObjectThread("t1");
        ChangeObjectThread t2 = new ChangeObjectThread("t2");

        t1.start();
        Thread.sleep(1000);

        t2.start();
        Thread.sleep(3000);

        t1.interrupt();
        LockSupport.unpark(t2);

        t1.join();
        t2.join();
    }
}
