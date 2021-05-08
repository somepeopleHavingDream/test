package org.yangxin.test.ratelimit;

/**
 * @author yangxin
 * 2021/4/25 下午7:59
 */
public class CounterTest {

    private Long timestamp = System.currentTimeMillis();
    private Integer requestCount = 0;

    public boolean grant() {
        long now = System.currentTimeMillis();

        /*
         * 时间窗口ms
         */
        long interval = 1000;
        if (now < timestamp + interval) {
            // 在时间窗口内
            requestCount++;

            // 判断当前时间窗口内是否超过最大请求控制数
            /*
             * 时间窗口内最大请求数
             */
            int limit = 100;
            return requestCount <= limit;
        } else {
            timestamp = now;

            // 超时后重置
            requestCount = 1;
            return true;
        }
    }
}
