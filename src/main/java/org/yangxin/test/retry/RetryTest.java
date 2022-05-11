package org.yangxin.test.retry;

/**
 * break retry
 *
 * @author yangxin
 * 2020/05/25 16:49
 */
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class RetryTest {

    public static void main(String[] args) {
//        int count = 0;
//
//        retry:
////        System.out.println("retry..");
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 5; j++) {
//                count++;
//                if (count == 3) {
//                    continue retry;
////                    break retry;
//                }
//                System.out.print(count + " ");
//            }
//        }

        testContinue();
        testBreak();
    }

    public static void testContinue() {
        retry:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(j + " ");

                if (j == 3) {
                    continue retry;
                }
            }
        }

        System.out.println(" >>> ok");
    }

    public static void testBreak() {
        retry:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(j + " ");

                if (j == 3) {
                    break retry;
                }
            }
        }

        System.out.println(" >>> ok");
    }
}
