package org.yangxin.test.jvm.jconsole;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 内存占位符对象，一个OomObject大约占64k
 *
 * @author yangxin
 * 2022/8/20 21:02
 */
@SuppressWarnings({"SameParameterValue", "MismatchedQueryAndUpdateOfCollection", "InfiniteLoopStatement"})
public class MemoryMonitor {

    private final byte[] PLACEHOLDER = new byte[64 * 1024];

    private static void fillHeap(int num) throws InterruptedException {
        List<MemoryMonitor> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            // 稍作延迟，令监视曲线变化更加明显
            Thread.sleep(50);
            list.add(new MemoryMonitor());
        }

        System.gc();
        TimeUnit.SECONDS.sleep(1);
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            fillHeap(1000);
        }
    }
}
