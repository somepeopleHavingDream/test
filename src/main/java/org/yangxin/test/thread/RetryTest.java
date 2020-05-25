package org.yangxin.test.thread;

/**
 * break retry
 *
 * @author yangxin
 * 2020/05/25 16:49
 */
public class RetryTest {

    public static void main(String[] args) {
        int count = 0;
        retry:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                count++;
                if (count == 3) {
                    continue retry;
//                    break retry;
                }
                System.out.print(count + " ");
            }
        }
    }
}
