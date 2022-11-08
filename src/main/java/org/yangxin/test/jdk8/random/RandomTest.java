package org.yangxin.test.jdk8.random;

import java.util.Random;

/**
 * @author yangxin
 * 2022/1/12 18:27
 */
@SuppressWarnings("unused")
public class RandomTest {

    public static void main(String[] args) {
//        test1();
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
