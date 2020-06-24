package org.yangxin.test.jvm.classrunning;

/**
 * 局部变量表Slot复用对垃圾收集的影响之一
 * -verbose:gc
 *
 * @author yangxin
 * 2020/06/24 11:06
 */
public class SlotTest1 {

    public static void main(String[] args) {
        byte[] placeholder = new byte[64 * 1024 * 1024];
        System.gc();
    }
}
