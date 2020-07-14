package org.yangxin.test.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 通过参数-XX:+HeapDumpOnOutOfMemoryError可以让虚拟机在出现内存溢出异常时Dump出当前内存堆转储快照
 *
 * @author yangxin
 * 2019/11/01 11:22
 */
public class HeapOOM {

    static class OOMObject {

    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "InfiniteLoopStatement"})
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
