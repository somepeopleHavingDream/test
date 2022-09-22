package org.yangxin.test.jdk8.forkjoin;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author yangxin
 * 2022/9/19 18:19
 */
public class ForkJoinTest {

    /**
     * 数组最大长度
     */
    private static final int ARRAY_LENGTH = 3000000;

    /**
     * 任务拆分最小单位，拆分到这个单位就不能拆分了，即阈值
     */
    private static final int SPLIT_MIN_THRESHOLD = (ARRAY_LENGTH / 10);

    /**
     * 准备数组
     *
     * @return 数组
     */
    public static int[] make() {
        int[] origin = new int[ARRAY_LENGTH];

        Random random = new Random();
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            origin[i] = random.nextInt(ARRAY_LENGTH * 3);
        }

        return origin;
    }

    /**
     * 创建ForkJoin框架，递归进入任务拆分，递归出来任务结果累加
     */
    @NoArgsConstructor
    @AllArgsConstructor
    private static class SumTask extends RecursiveTask<Integer> {

        private int[] recursiveArray;

        /**
         * 划分开始位置
         */
        private int from;

        /**
         * 划分结束位置
         */
        private int to;

        @Override
        protected Integer compute() {
            // 判断当前数组是否小于最小长度阈值，没有达到则进行拆分
            if (to - from < SPLIT_MIN_THRESHOLD) {
                // 满足阈值的数组处理，累加
                int sum = 0;
                for (int i = from; i < to; i++) {
                    sum += recursiveArray[i];
                }
                return sum;
            }

            // 数组长度划半
            int half = from + (to - from) / 2;
            // 递归拆分
            SumTask left = new SumTask(recursiveArray, from, half);
            SumTask right = new SumTask(recursiveArray, half + 1, to);
            invokeAll(left, right);

            // 等待最小阈值求和完毕，再累加返回给上一层join任务
            return left.join() + right.join();
        }
    }

    public static void main(String[] args) {
        int[] array = make();

        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(array, 0, array.length - 1);
        long recordTime = System.currentTimeMillis();
        pool.submit(task);

        System.out.println(task.join());
        System.out.println(System.currentTimeMillis() - recordTime);
    }
}
