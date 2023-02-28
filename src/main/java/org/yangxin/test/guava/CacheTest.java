package org.yangxin.test.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2022/11/2 17:58
 */
@SuppressWarnings({"NullableProblems", "unused"})
public class CacheTest {

    private static final Cache<Integer, Integer> NUM_CACHE1 = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build();

    private static final LoadingCache<Integer, Integer> NUM_CACHE2 = CacheBuilder.newBuilder()
            .expireAfterWrite(5L, TimeUnit.SECONDS)
            .maximumSize(5000L)
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
     * 使用自定义ClassLoader加载数据，置入内存中
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
    }

    /**
     * Cache，显式put操作置入内存
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
