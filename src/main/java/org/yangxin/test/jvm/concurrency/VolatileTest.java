package org.yangxin.test.jvm.concurrency;

/**
 * volatile变量自增运算测试
 *
 * @author yangxin
 * 2020/07/20 16:20
 */
public class VolatileTest {

    public static volatile int race = 0;

    @SuppressWarnings("NonAtomicOperationOnVolatileField")
    public static void increase() {
        race++;
    }

    private static final Integer THREADS_COUNT = 20;

    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }

        // 等待所有累加线程都结束
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(race);
    }
}
