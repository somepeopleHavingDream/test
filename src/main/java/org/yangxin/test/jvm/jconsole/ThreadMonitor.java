package org.yangxin.test.jvm.jconsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author yangxin
 * 2022/8/20 22:02
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread", "StatementWithEmptyBody", "AlibabaUndefineMagicConstant"})
public class ThreadMonitor {

    /**
     * 线程死循环演示
     */
    private static void createBusyThread() {
        Thread thread = new Thread(() -> {
            while (true) {
            }
        }, "testBusyThread");
        thread.start();
    }

    /**
     * 线程锁等待演示
     *
     * @param lock 锁
     */
    private static void createLockThread(final Object lock) {
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "testLockThread");
        thread.start();
    }

    /**
     * 线程死锁等待演示
     *
     * @author yangxin
     * 2022/08/20 22:52
     */
    private static class SynAddRunnable implements Runnable {

        private final int a;
        private final int b;

        public SynAddRunnable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        test1();
        test2();
    }

    private static void test2() {
        for (int i = 0; i < 100; i++) {
            new Thread(new SynAddRunnable(1, 2)).start();
            new Thread(new SynAddRunnable(2, 1)).start();
        }
    }

    private static void test1() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();

        createBusyThread();
        reader.readLine();

        createLockThread(new Object());
    }
}
