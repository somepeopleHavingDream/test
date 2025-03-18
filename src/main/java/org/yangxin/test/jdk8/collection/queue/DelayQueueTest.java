package org.yangxin.test.jdk8.collection.queue;

import org.springframework.lang.NonNull;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    private static void test1() throws InterruptedException {
        // 创建 DelayQueue 实例
        DelayQueue<DelayedElement> delayQueue = new DelayQueue<>();

        // 添加延迟元素
        delayQueue.put(new DelayedElement("Task1", 2000));  // 2 秒后到期
        delayQueue.put(new DelayedElement("Task2", 5000));  // 5 秒后到期

        // 取出并处理到期元素
        while (!delayQueue.isEmpty()) {
            DelayedElement element = delayQueue.take();
            System.out.println("Processed: " + element);
        }
    }

    /**
     * 实现 Delayed 接口的元素类
     */
    private static class DelayedElement implements Delayed {

        private final String name;
        private final long expireTime;

        public DelayedElement(String name, long delayMillis) {
            this.name = name;
            this.expireTime = System.currentTimeMillis() + delayMillis;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long remainingTime = expireTime - System.currentTimeMillis();
            return unit.convert(remainingTime, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(@NonNull Delayed o) {
            return Long.compare(this.expireTime, ((DelayedElement) o).expireTime);
        }

        @Override
        public String toString() {
            return "DelayedElement{" +
                    "name='" + name + '\'' +
                    ", expireTime=" + expireTime +
                    '}';
        }
    }
}
