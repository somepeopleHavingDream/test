package org.yangxin.test.hutool.id;

import cn.hutool.core.util.IdUtil;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class IdTest {
    public static void main(String[] args) {
//        test1();
        test2();
//        test3();
    }

    private static void test3() {
        for (int i = 0; i < 100; i++) {
            long nextId = IdUtil.getSnowflake().nextId();
            System.out.println(nextId);
        }
    }

    @SneakyThrows
    private static void test2() {
        /*
            生成的 id 基本上是偶数
         */

        for (int i = 0; i < 100; i++) {
            TimeUnit.SECONDS.sleep(1);
            long nextId = IdUtil.getSnowflake().nextId();
            System.out.println(nextId);
        }
    }

    private static void test1() {
        long nextId = IdUtil.getSnowflake().nextId();
        System.out.println(nextId);
    }
}
