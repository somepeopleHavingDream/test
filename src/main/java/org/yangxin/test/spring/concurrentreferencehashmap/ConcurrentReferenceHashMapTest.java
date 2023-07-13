package org.yangxin.test.spring.concurrentreferencehashmap;

import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2023/7/11 18:39
 */
public class ConcurrentReferenceHashMapTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        /*
            SOFT和WEAK是两种不同的引用类型，它们的区别在于对象被回收的时机：
            SOFT引用的对象只有在内存不足时，才会被回收。
            WEAK引用的对象不管内存够不够，都会回收。
         */

        ConcurrentReferenceHashMap<String, String> map
                = new ConcurrentReferenceHashMap<>(16, ConcurrentReferenceHashMap.ReferenceType.WEAK);
        map.put("key", "value");

        System.out.println(map);
        System.gc();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(map);
    }
}
