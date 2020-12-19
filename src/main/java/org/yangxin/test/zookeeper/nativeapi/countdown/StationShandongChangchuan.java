package org.yangxin.test.zookeeper.nativeapi.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * @author yangxin
 * 2020/07/17 13:50
 */
public class StationShandongChangchuan extends DangerCenter {

    public StationShandongChangchuan(CountDownLatch countDownLatch) {
        super(countDownLatch, "山东长川调度站");
    }

    @Override
    protected void check() {
        System.out.println("正在检查【" + this.getStation() + "】……");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("检查【" + this.getStation() + "】完毕，可以发车。");
    }
}
