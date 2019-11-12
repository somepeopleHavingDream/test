package org.yangxin.test.jvm.gc;

/**
 * 垃圾回收算法-引用计数算法
 * testGC()方法执行后，objA和objB会不会被GC呢？
 *
 * @author yangxin
 * 2019/11/12 17:49
 */
public class ReferenceCountingGC {
    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        // 假设在这里发生gc，那么objA和objB是否能被回收？
        System.gc();
    }

    public static void main(String[] args) {
        testGC();
    }
}
