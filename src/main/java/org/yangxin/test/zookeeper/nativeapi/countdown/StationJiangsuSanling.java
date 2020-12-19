package org.yangxin.test.zookeeper.nativeapi.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * @author yangxin
 * 2020/07/17 13:49
 */
public class StationJiangsuSanling extends DangerCenter {

    public StationJiangsuSanling(CountDownLatch countDownLatch) {
        super(countDownLatch, "江苏三林调度站");
    }

    @Override
    protected void check() {
        System.out.println("正在检查【" + this.getStation() + "】……");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("检查【" + this.getStation() + "】完毕，可以发车。");
    }
}
