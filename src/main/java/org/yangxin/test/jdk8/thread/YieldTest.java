package org.yangxin.test.jdk8.thread;

/**
 * @author yangxin
 * 2022/1/12 21:03
 */
@SuppressWarnings({"AlibabaUndefineMagicConstant", "AlibabaAvoidManuallyCreateThread"})
public class YieldTest implements Runnable {

    private final String name;

    public YieldTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println(name + "——" + i);

            // 当i为30时，该线程就会把cpu时间让掉，让其他或者自己的线程执行（也就是谁想抢到谁先执行）
            if (i == 30) {
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new YieldTest("Alice")).start();
        new Thread(new YieldTest("Bob")).start();
    }
}
