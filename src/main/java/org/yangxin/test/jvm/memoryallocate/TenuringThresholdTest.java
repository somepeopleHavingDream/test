package org.yangxin.test.jvm.memoryallocate;

/**
 * 长期存活的对象进入老年代
 *
 * @author yangxin
 * 2020/04/20 15:33
 */
public class TenuringThresholdTest {

    private static final Integer _1MB = 1024 * 1024;

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution -XX:+PrintGCDetails -XX:+UseParNewGC
     *
     * 这个例子也证明了，gc的时机并不一定等gc失败的时候再发生，而是有gc线程决定什么时候进行gc
     */
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4];

        // 什么时候进入老年代取决于XX:MaxTenuringThreshold设置
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }
}
