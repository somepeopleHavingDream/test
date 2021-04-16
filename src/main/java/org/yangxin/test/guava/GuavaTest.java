package org.yangxin.test.guava;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author yangxin
 * 2021/4/12 9:40
 */
@SuppressWarnings("UnstableApiUsage")
public class GuavaTest {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(2);
    }
}
