package org.yangxin.test.localcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

public class EhcacheCacheTest {

    public static void main(String[] args) {
        // 创建缓存管理器
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        // 创建缓存
        Cache<String, String> cache = cacheManager.createCache(
                "myCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        String.class, String.class, ResourcePoolsBuilder.heap(100)
                )
        );

        // 添加缓存
        cache.put("key1", "value1");

        // 获取缓存
        String value = cache.get("key1");
        System.out.println("Value: " + value);

        // 移除缓存
        cache.remove("key1");

        // 关闭缓存管理器
        cacheManager.close();
    }
}
