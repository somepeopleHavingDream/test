package org.yangxin.test.jvm.classrunning;

/**
 * 局部变量表Slot复用对垃圾收集的影响之三
 * -verbose:gc
 *
 * @author yangxin
 * 2020/07/15 09:25
 */
public class SlotTest3 {

    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
    }
}
