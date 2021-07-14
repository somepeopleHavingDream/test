package org.yangxin.test.weakreference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author yangxin
 * 2020/12/12 20:40
 */
public class Client {

    public static void main(String[] args) {
//        client1Main();
        client2Main();
    }

    private static void client2Main() {
        ReferenceQueue<Apple> appleReferenceQueue = new ReferenceQueue<>();
        WeakReference<Apple> appleWeakReference1 = new WeakReference<>(new Apple("青苹果"), appleReferenceQueue);
        WeakReference<Apple> appleWeakReference2 = new WeakReference<>(new Apple("红苹果"), appleReferenceQueue);

        System.out.println("gc调用前");

        Reference<? extends Apple> reference1;
        while ((reference1 = appleReferenceQueue.poll()) != null) {
            // 不会输出，因为没有回收被弱引用的对象，并不会加入队列中
            System.out.println(reference1);
        }

        System.out.println(appleWeakReference1.get());
        System.out.println(appleWeakReference2.get());

        System.out.println("调用gc");
        System.gc();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("调用gc后");

        // 下面两个输出为null，表示对象被回收了
        System.out.println(appleWeakReference1.get());
        System.out.println(appleWeakReference2.get());

        // 输出结果，并且就是上面的appleWeakReference1和appleWeakReference2，再次证明对象被回收了
        Reference<? extends Apple> reference2;
        while ((reference2 = appleReferenceQueue.poll()) != null) {
            // 如果使用继承的方式就可以包含其他信息了
            System.out.println("appleReferenceQueue中：" + reference2);
        }
    }

    private static void client1Main() {
        Salad salad = new Salad(new Apple("红富士"));

        // 通过WeakReference的get()方法获取Apple
        System.out.println("Apple: " + salad.get());

        System.gc();
        try {
            // 休眠一下，在运行的时候加载虚拟机参数-XX:+PrintGCDetails，输出gc信息，确定gc发生了
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 如果为空，代表弱引用对象被回收了
        if (salad.get() == null) {
            System.out.println("clear Apple.");
        }
    }
}
