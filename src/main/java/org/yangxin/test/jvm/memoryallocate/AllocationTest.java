package org.yangxin.test.jvm.memoryallocate;

/**
 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * 虚拟机提供了-XX:PrintGCDetails这个收集器日志参数，告诉虚拟机在发生垃圾收集行为时打印内存回收日志，并且在进程退出的时候输出当前内存各区域的分配情况。
 * 在实际应用中，内存回收日志一般是打印到文件中通过日志工具进行分析，不过本实验的日志并不多，直接阅读就能看得很清楚。
 */
public class AllocationTest {

    private static final int _1MB = 1024 * 1024;

    private static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];

        // 这个地方和《深入理解jvm虚拟机》中所描述的不一样，此处allocation4直接被jvm判定为大对象被分配到了老年代
        // 有两种情况，对象会直接分配到老年代
        // 1：如果在新生代分配失败且对象是一个不含任何对象引用的大数组，可被直接分配到老年代，通过在老年代的分配避免新生代的一次垃圾回收
        // 2：XX:PretenureSizeThreshold=字节大小可以设分配到新生代对象的大小限制。任何比这个大的对象都不会尝试在新生代分配，将在老年代分配内存
        allocation4 = new byte[4 * _1MB];   // 出现一次Minor GC
    }

    public static void main(String[] args) {
        testAllocation();
    }
}
