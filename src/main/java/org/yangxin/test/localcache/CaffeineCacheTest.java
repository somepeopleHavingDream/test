package org.yangxin.test.localcache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.stats.CacheStats;

import java.util.concurrent.TimeUnit;

public class CaffeineCacheTest {

    public static void main(String[] args) {
        // 创建缓存
        Cache<String, String> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .recordStats()
                .build();

        // 添加缓存
        cache.put("key1", "value1");

        // 获取缓存
        String value = cache.getIfPresent("key1");
        System.out.println("Value: " + value);

        // 移除缓存
        cache.invalidate("key1");

        // 获取统计信息
        CacheStats stats = cache.stats();
        System.out.println("Hit Rate: " + stats.hitRate());
        System.out.println("Miss Rate: " + stats.missRate());
        System.out.println("Eviction Count: " + stats.evictionCount());
    }
}
