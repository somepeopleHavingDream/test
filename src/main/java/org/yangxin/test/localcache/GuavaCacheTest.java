package org.yangxin.test.localcache;

import com.google.common.cache.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2022/11/2 17:58
 */
@SuppressWarnings({"NullableProblems", "unused"})
public class GuavaCacheTest {

    private static final Cache<Integer, Integer> NUM_CACHE1 = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build();

    private static final LoadingCache<Integer, Integer> NUM_CACHE2 = CacheBuilder.newBuilder()
            .expireAfterWrite(5L, TimeUnit.SECONDS)
            .maximumSize(5000L)
            .recordStats()
            .build(new CacheLoader<Integer, Integer>() {
                @Override
                public Integer load(Integer key) {
                    System.out.println("no cache");
                    return key * 5;
                }
            });

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        test1();
        test2();
    }

    /**
     * 使用自定义 ClassLoader 加载数据，置入内存中
     *
     * @throws ExecutionException 执行异常
     * @throws InterruptedException 中断异常
     */
    private static void test2() throws ExecutionException, InterruptedException {
        System.out.println(NUM_CACHE2.get(1));
        Thread.sleep(1000);

        System.out.println(NUM_CACHE2.get(1));
        Thread.sleep(1000);

        NUM_CACHE2.put(1, 6);
        System.out.println(NUM_CACHE2.get(1));

        // 获取统计信息
        CacheStats stats = NUM_CACHE2.stats();
        System.out.println("Hit Rate: " + stats.hitRate());
        System.out.println("Miss Rate: " + stats.missRate());
        System.out.println("Eviction Count: " + stats.evictionCount());
    }

    /**
     * Cache，显式 put 操作置入内存
     *
     * @throws InterruptedException 被中断异常
     */
    private static void test1() throws InterruptedException {
        System.out.println(NUM_CACHE1.getIfPresent(1));
        Thread.sleep(1000);

        System.out.println(NUM_CACHE1.getIfPresent(1));
        Thread.sleep(1000);

        NUM_CACHE1.put(1, 5);
        System.out.println(NUM_CACHE1.getIfPresent(1));

        Thread.sleep(6000);
        System.out.println(NUM_CACHE1.getIfPresent(1));
    }
}
