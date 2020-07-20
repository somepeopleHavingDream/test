package org.yangxin.test.jvm.compileroptimize;

/**
 * 查看与分析即时编译结果
 *
 * @author yangxin
 * 2020/07/20 14:02
 */
public class ObserveJIT {

    public static final Integer NUM = 15000;

    public static int doubleValue(int i) {
        return i * 2;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static Long calcSum() {
        long sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += doubleValue(i);
        }
        return sum;
    }

    /**
     * -XX:+PrintCompilation
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {
        for (int i = 0; i < NUM; i++) {
            calcSum();
        }
    }
}
