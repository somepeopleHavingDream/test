package org.yangxin.test.thread;

/**
 * 通过notify、wait打印0~100的奇偶数
 *
 * @author yangxin
 * 2020/02/01 19:20
 */
public class PrintOddEvenByWaitNotify {
    /**
     * 打印次数
     */
    private static int count;

    /**
     * 锁
     */
    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new PrintNum()).start();
        new Thread(new PrintNum()).start();
    }

    /**
     * 打印一个数
     *
     * @author yangxin
     * 2020/02/01 19:21
     */
    static class PrintNum implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                while (count <= 100) {
                    // 打印当前数，并加1
                    System.out.println(count++);

                    // 通知其他线程
                    lock.notifyAll();

                    // 进入休眠状态
                    if (count < 100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}


