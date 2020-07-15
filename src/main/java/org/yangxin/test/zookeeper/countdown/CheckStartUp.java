package org.yangxin.test.zookeeper.countdown;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yangxin
 * 2020/07/15 13:41
 */
public class CheckStartUp {

    public CheckStartUp() {}

    public static boolean checkAllStations() throws InterruptedException {
        // 初始化3个调度站
        CountDownLatch countDownLatch = new CountDownLatch(3);

        // 把所有站点添加进list
        List<DangerCenter> stationList = new ArrayList<>();
        stationList.add(new StationBeijingIMooc(countDownLatch));
        stationList.add(new StationJiangsuSanling(countDownLatch));
        stationList.add(new StationShandongChangchuan(countDownLatch));

        // 使用线程池
        ExecutorService executorService = Executors.newFixedThreadPool(stationList.size());
        stationList.forEach(executorService::execute);

        // 等待线程执行完毕
        countDownLatch.await();

        for (DangerCenter dangerCenter : stationList) {
            if (!dangerCenter.isOk()) {
                return false;
            }
        }
        long count = countDownLatch.getCount();

        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        boolean result = CheckStartUp.checkAllStations();
        System.out.println("监控中心针对所有危化品调度站点的检查结果为：" + result);
    }
}
