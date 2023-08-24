package org.yangxin.test.spring.util.stopwatch;

import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2022/4/18 10:54
 */
public class StopWatchTest {

    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    private static void test1() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("删除消息相关推送开始");
        TimeUnit.SECONDS.sleep(5);
        stopWatch.stop();
        stopWatch.prettyPrint();
    }
}
