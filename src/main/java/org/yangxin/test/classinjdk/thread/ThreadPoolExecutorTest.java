package org.yangxin.test.classinjdk.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 12/27/20 11:13 PM
 */
@SuppressWarnings("AlibabaThreadShouldSetName")
public class ThreadPoolExecutorTest implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,
                4,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.execute(new ThreadPoolExecutorTest());
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println("threadPoolExecutor: " + threadPoolExecutor);
        System.out.println("activeCount: " + threadPoolExecutor.getActiveCount());
        System.out.println("completedTaskCount: " + threadPoolExecutor.getCompletedTaskCount());
        System.out.println("corePoolSize: " + threadPoolExecutor.getCorePoolSize());
        System.out.println("largestPoolSize: " + threadPoolExecutor.getLargestPoolSize());
        System.out.println("maximumPoolSize: " + threadPoolExecutor.getMaximumPoolSize());
        System.out.println("poolSize: " + threadPoolExecutor.getPoolSize());
        System.out.println("taskCount: " + threadPoolExecutor.getTaskCount());
    }

    @Override
    public void run() {
        System.out.println("ok.");
    }
}
