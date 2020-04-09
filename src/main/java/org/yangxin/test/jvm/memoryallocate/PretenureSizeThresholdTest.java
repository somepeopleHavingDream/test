package org.yangxin.test.jvm.memoryallocate;

/**
 * 大对象直接进入老年代
 *
 * @author yangxin
 * 2020/04/08 17:49
 */
public class PretenureSizeThresholdTest {

    private static final Integer _1MB = 1024 * 1024;

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=3145728
     */
    public static void main(String[] args) {
        byte[] allocation;
        // 直接分配在老年代
        allocation = new byte[4 * _1MB];
    }
}
