package org.yangxin.test.zookeeper.nativeapi.countdown;

import lombok.Data;

import java.util.concurrent.CountDownLatch;


/**
 * 抽象类，用于演示危险品化工车监控中心，统一检查
 *
 * @author yangxin
 * 2020/07/15 11:43
 */
@Data
public abstract class DangerCenter implements Runnable {

    /**
     * 计数器
     */
    private CountDownLatch countDownLatch;

    /**
     * 调度站
     */
    private String station;

    /**
     * 调度站针对当前自己的站点进行检查，是否检查ok的标志
     */
    private boolean ok;

    public DangerCenter(CountDownLatch countDownLatch, String station) {
        this.countDownLatch = countDownLatch;
        this.station = station;
        this.ok = false;
    }

    @Override
    public void run() {
        try {
            check();
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }

    /**
     * 检查危化品车
     * 蒸罐
     * 汽油
     * 轮胎
     * gps
     * ……
     */
    protected abstract void check();
}
