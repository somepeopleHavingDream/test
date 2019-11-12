package org.yangxin.test.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池OOM
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * @author yangxin
 * 2019/11/12 16:47
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        // 使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<>();

        // 10MB的PermSize在integer范围内足够产生oom
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
