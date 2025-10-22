package org.yangxin.test.guava;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class LoadingCacheTest {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                .softValues()
                .initialCapacity(10_000)
                .maximumSize(10_000)
                .build(key -> key + 1);
        Integer result = cache.get(1);
        System.out.println(result);
    }

    private static void test1() {
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                .maximumSize(10_000)                         // 最大缓存条目数
                .expireAfterWrite(10, TimeUnit.MINUTES)      // 写入10分钟后过期
                .refreshAfterWrite(1, TimeUnit.MINUTES)      // 写入1分钟后刷新（异步）
                .recordStats()                               // 开启统计功能
                .build(key -> key + 1);          // 缓存加载逻辑

// 使用缓存
        Integer value = cache.get(1);// 自动加载并缓存
        System.out.println(value);
    }
}
