package org.yangxin.test.jdk8.object;

/**
 * @author yangxin
 * 2021/4/19 15:51
 */
public class ObjectTest {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        int count0 = 0;
        int count1 = 0;
        int loop = 60_000_000;
        for (int i = 0; i < loop; i++) {
            int value = (i + "").hashCode() % 2;
            if (value == 0) {
                count0++;
            } else if (value == 1) {
                count1++;
            }
        }

        int anotherCount0 = 0;
        int anotherCount1 = 0;
        for (int i = 0; i < loop; i++) {
            int value = (i + "" + System.currentTimeMillis()).hashCode() % 2;
            if (value == 0) {
                anotherCount0++;
            } else if (value == 1) {
                anotherCount1++;
            }
        }

        System.out.println(count0 + " " + count1 + " " + anotherCount0 + " " + anotherCount1);
    }
}
