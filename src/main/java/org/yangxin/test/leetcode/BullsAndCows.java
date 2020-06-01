package org.yangxin.test.leetcode;

/**
 * @author yangxin
 * 2020/06/01 14:47
 */
public class BullsAndCows {

    public static void main(String[] args) {
        // 1A3B
        System.out.println(getHint("1807", "7810"));
        // 1A1B
        System.out.println(getHint("1123", "0111"));
        // 1A0B
        System.out.println(getHint("11", "10"));
        // 1A0B
        System.out.println(getHint("11", "01"));
        // 0A1B
        System.out.println(getHint("1122", "0001"));
    }

    private static String getHint(String secret, String guess) {
        int[] secretArray = new int[10];
        int[] guessArray = new int[10];
        int bulls = 0, total = 0;
        for (int i = 0; i < secret.length(); i++) {
            secretArray[secret.charAt(i) - '0']++;
            guessArray[guess.charAt(i) - '0']++;

            // 统计相同位置，相同数字的个数
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            }
        }

        // 相同数字，不一定相同位置的数字的个数
        for (int i = 0; i < secretArray.length; i++) {
            total += Math.min(secretArray[i], guessArray[i]);
        }

        return String.valueOf(bulls) + 'A' + (total - bulls) + 'B';
    }
}
