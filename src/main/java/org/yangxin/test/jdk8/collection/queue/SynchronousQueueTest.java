package org.yangxin.test.jdk8.collection.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2024/11/25 11:20
 */
@SuppressWarnings("CallToPrintStackTrace")
public class SynchronousQueueTest {

    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + "\t 入队列 1");
                synchronousQueue.put("1");
                System.out.println(threadName + "\t 入队列 2");
                synchronousQueue.put("2");
                System.out.println(threadName + "\t 入队列 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAAAA").start();

        new Thread(() -> {
            try {
                String threadName = Thread.currentThread().getName();

                TimeUnit.SECONDS.sleep(5);
                System.out.println(threadName + "\t 出队列 " + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(threadName + "\t 出队列 " + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(threadName + "\t 出队列 " + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBBBB").start();
    }
}
