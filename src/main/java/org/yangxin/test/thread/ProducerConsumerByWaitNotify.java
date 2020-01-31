package org.yangxin.test.thread;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 通过wait、notify实现生产者消费者模式
 *
 * @author yangxin
 * 2020/01/31 18:32
 */
public class ProducerConsumerByWaitNotify {
    public static void main(String[] args) {
        StorageQueue storageQueue = new StorageQueue();
        Producer producer = new Producer(storageQueue);
        Consumer consumer = new Consumer(storageQueue);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
    }
}

/**
 * 生产者
 *
 * @author yangxin
 * 2020/01/31 18:32
 */
class Producer implements Runnable {
    /**
     * 存储队列
     */
    private StorageQueue storageQueue;

    Producer(StorageQueue storageQueue) {
        this.storageQueue = storageQueue;
    }

    @Override
    public void run() {
        // 生产
        try {
            for (int i = 0; i < 100; i++) {
                storageQueue.add();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    /**
     * 存储队列
     */
    private StorageQueue storageQueue;

    Consumer(StorageQueue storageQueue) {
        this.storageQueue = storageQueue;
    }

    @Override
    public void run() {
        // 消费
        try {
            for (int i = 0; i < 100; i++) {
                storageQueue.take();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 存储队列
 *
 * @author yangxin
 * 2020/01/31 18:34
 */
class StorageQueue {
    /**
     * 最大容量
     */
    private int maxSize;

    /**
     * 存储队列
     */
    private Queue<Date> queue;

    StorageQueue() {
        this.maxSize = 10;
        this.queue = new LinkedList<>();
    }

    /**
     * 生产
     */
    synchronized void add() throws InterruptedException {
        // 如果当前队列满，则通知消费者消费，不再继续生产
//        if (maxSize == queue.size()) {
//            wait();
//        }
        while (maxSize == queue.size()) {
            wait();
        }

        queue.add(new Date());
        System.out.println("已生产一个元素，当前仓库容量为：" + queue.size());
        notifyAll();
    }

    /**
     * 拿出一个元素
     */
    synchronized void take() throws InterruptedException {
        // 如果当前存储队列中没有元素以供消费，则等待生产者生产
//        if (queue.size() == 0) {
//            wait();
//        }
        while (queue.size() == 0) {
            wait();
        }

        // 消费
        queue.poll();
        System.out.println("已消费一个元素，当前仓库容量为：" + queue.size());
        notifyAll();
    }
}
