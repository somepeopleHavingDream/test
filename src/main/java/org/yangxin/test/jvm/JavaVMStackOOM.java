package org.yangxin.test.jvm;

/**
 * 不断创建线程，让栈区域抛出OOM异常。
 * 这样产生的内存溢出异常与栈空间是否足够大并不存在任何关系，或者准确地说，在这种情况下，
 * 给每个线程的栈分配的内存越大，反而越容易产生内存溢出异常
 * -Xss2M
 *
 * @author yangxin
 * 2019/11/12 16:12
 */
public class JavaVMStackOOM {
    private void dontStop() {
        while (true) {

        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
