package org.yangxin.test.jvm.classrunning;

/**
 * 局部变量表Slot复用对垃圾收集的影响之二
 * -verbose:gc
 *
 * @author yangxin
 * 2020/07/15 09:23
 */
public class SlotTest2 {

    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        System.gc();
    }
}
