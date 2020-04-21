package org.yangxin.test.jvm.memoryallocate;

/**
 * 空间分配担保
 * 注意：HandlePromotionFailure参数好像在现代版本的虚拟机中不起作用了，考虑一下PromotionFailureALot
 *
 * @author yangxin
 * 2020/04/21 16:08
 */
public class HandlePromotionTest {

    private static final Integer _1MB = 1024 * 1024;

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:HandlePromotionFailure=false -XX:+PrintGCDetails
     */
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null;
        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * _1MB];
    }
}
