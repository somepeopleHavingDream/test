package org.yangxin.test.jvm.oom;

/**
 * 不断创建线程，让栈区域抛出OOM异常。
 * 这样产生的内存溢出异常与栈空间是否足够大并不存在任何关系，或者准确地说，在这种情况下，
 * 给每个线程的栈分配的内存越大，反而越容易产生内存溢出异常。
 *
 * 特别提示一下，如果读者要尝试运行上面这段代码，记得要先保存当前的工作，有可能Java的线程是映射到操作系统的内核线程上的，
 * 所以以下代码执行时有较大的风险，可能会导致操作 系统假死。
 *
 * -Xss2M
 *
 * @author yangxin
 * 2019/11/12 16:12
 */
public class JavaVMStackOOM {

    @SuppressWarnings({"StatementWithEmptyBody"})
    private void dontStop() {
        while (true) {

        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(this::dontStop);
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
