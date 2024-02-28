package org.yangxin.test.netty.demo;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2023/12/25 18:02
 */
public class MyTimerTask implements TimerTask {

    private final Boolean sleepAlways;

    public MyTimerTask(Boolean sleepAlways) {
        this.sleepAlways = sleepAlways;
    }

    @Override
    public void run(Timeout timeout) throws Exception {
        if (sleepAlways) {
            Thread.sleep(Long.MAX_VALUE);
        }

        System.out.println(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        Timer timer = new HashedWheelTimer();
        MyTimerTask myTask1 = new MyTimerTask(false);
        MyTimerTask myTask2 = new MyTimerTask(false);

        // 在5秒后执行定时任务
        timer.newTimeout(myTask1, 5, TimeUnit.SECONDS);
        timer.newTimeout(myTask2, 2, TimeUnit.SECONDS);
    }
}
