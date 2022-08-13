package org.yangxin.test.batch;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 分治工具类
 *
 * @author yangxin
 * 2022/8/13 11:43
 */
public class SplitListUtil {

    /**
     * 拆分集合
     *
     * @param list 需要拆分的集合
     * @param subListLength 每个子集合的元素个数
     * @return 返回拆分后的各个集合组成的列表
     * @param <T> 泛型对象
     */
    public static <T> List<List<T>> split(List<T> list, int subListLength) {
        // 检查参数
        if (CollectionUtils.isEmpty(list) || subListLength <= 0) {
            return Collections.emptyList();
        }

        // 存放结果
        List<List<T>> result = new LinkedList<>();

        int size = list.size();
        if (size <= subListLength) {
            // 数据量不足subListLength指定的大小
            result.add(list);
            return result;
        }

        int pre = size /subListLength;
        int last = size % subListLength;

        // 前面pre个集合，每个大小都是subListLength个元素
        for (int i = 0; i < pre; i++) {
            List<T> tmp = new LinkedList<>();
            for (int j = 0; j < subListLength; j++) {
                tmp.add(list.get(i * subListLength + j));
            }
            result.add(tmp);
        }

        // last的处理，可能不足subListLength个元素
        if (last <= 0) {
            return result;
        }

        List<T> tmp = new LinkedList<>();
        for (int i = 0; i < last; i++) {
            tmp.add(list.get(pre * subListLength + i));
        }
        result.add(tmp);

        return result;
    }

    public static void main(String[] args) {
        // 准备数据
        List<String> list = new LinkedList<>();
        final int size = 1099;
        for (int i = 0; i < size; i++) {
            list.add("hello-" + i);
        }

        // 大集合里面包含多少小集合
        List<List<String>> all = split(list, 100);

        // 初始化线程池，调好参数
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(20,
                50,
                4,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.AbortPolicy());

        // 记录单个任务的执行次数
        CountDownLatch countDownLatch = new CountDownLatch(all.size());

        // 对大集合里面的每个小集合进行操作
        for (List<String> part : all) {
            // 交由线程池执行
            threadPool.execute(() -> part.forEach(System.out::println));

            // 任务个数-1，找到为0时唤醒await()
            countDownLatch.countDown();
        }

        try {
            // 让当前线程处于阻塞状态，直到所有任务提交完成
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        threadPool.shutdown();
        //noinspection StatementWithEmptyBody
        while (!threadPool.isTerminated()) {
        }
        System.out.println("所有任务完成");
    }
}
