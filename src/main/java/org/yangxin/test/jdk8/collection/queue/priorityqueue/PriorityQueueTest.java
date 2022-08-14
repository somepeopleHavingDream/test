package org.yangxin.test.jdk8.collection.queue.priorityqueue;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * @author yangxin
 * 2022/2/23 22:05
 */
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class PriorityQueueTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Random random = new Random();

        // 优先队列自然排序示例
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < 7; i++) {
            queue.add(random.nextInt(100));
        }
        for (int i = 0; i < 7; i++) {
            Integer poll = queue.poll();
            System.out.println(poll);
        }
    }
}
