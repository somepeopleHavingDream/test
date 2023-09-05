package org.yangxin.test.jdk8.random;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author yangxin
 * 2022/11/8 22:59
 */
public class ThreadLocalRandomTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        // ThreadLocalRandom能够解决多个线程发生的竞争问题
        ThreadLocalRandom random = ThreadLocalRandom.current();

        // 随机生成0~50的随机数，不包括50
        System.out.println(random.nextInt(50));
        // 随机生成30~50的随机数，不包括50
        System.out.println(random.nextInt(30, 50));
    }
}
