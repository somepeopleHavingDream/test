package org.yangxin.test.loop;

public class LoopTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 3809 525 666666
        int count = 2000000;
//        int count = 2000000;
        for (int i = 0; i < count; i++) {
            System.out.println(i);
        }
        long end = System.currentTimeMillis();
        long elapsed = end - start;

        System.out.println(elapsed);
        System.out.println(count / elapsed);
        System.out.println(count / (elapsed / 1000));
    }
}
