package org.yangxin.test.jdk8.random;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author yangxin
 * 2022/1/12 18:27
 */
public class RandomTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        // ThreadLocalRandom能够解决多个线程发生的竞争问题
        ThreadLocalRandom random = ThreadLocalRandom.current();

        // 随机生成0~50的随机数，不包括50
        System.out.println(random.nextInt(50));
        // 随机生成30~50的随机数，不包括50
        System.out.println(random.nextInt(30, 50));
    }

    private static void test1() {
        Random random = new Random();

        System.out.println(random.nextBoolean());
        // 随机生成0~50的随机数，不包括50
        System.out.println(random.nextInt(50));
        // 随机生成30~50的随机数，不包括50
        System.out.println(random.nextInt(20) + 30);
    }
}
