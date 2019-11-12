package org.yangxin.test.jvm;

/**
 * 虚拟机和本机方法栈StackOverflowError异常
 * -Xss256k
 *
 * @author yangxin
 * 2019/11/12 16:04
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length: " + oom.stackLength);
            throw e;
        }
    }
}
