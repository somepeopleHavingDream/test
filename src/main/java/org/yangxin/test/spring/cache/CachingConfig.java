package org.yangxin.test.spring.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author yangxin
 * 2022/5/13 14:24
 */
@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
    public Book book() {
        return new Book();
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Collections.singletonList(new ConcurrentMapCache("sampleCache")));

        return manager;
    }
}
