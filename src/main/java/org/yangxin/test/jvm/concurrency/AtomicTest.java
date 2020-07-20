package org.yangxin.test.jvm.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic变量自增运算测试
 *
 * @author yangxin
 * 2020/07/20 17:54
 */
public class AtomicTest {

    public static AtomicInteger race = new AtomicInteger(0);

    public static void increase() {
        race.incrementAndGet();
    }

    private static final Integer THREADS_COUNT = 20;

    @SuppressWarnings({"DuplicatedCode", "ConstantConditions"})
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

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println(race);
    }
}
