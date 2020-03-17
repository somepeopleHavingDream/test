package org.yangxin.test.aqs;

/**
 * @author yangxin
 * 2020/03/17 11:42
 */
public class MyLockTest {

    static int count = 0;
    static MyLock myLock = new MyLock();

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            try {
                myLock.lock();

                for (int i = 0; i < 10000; i++) {
                    count++;
                }
            } finally {
                myLock.unlock();
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }
}
