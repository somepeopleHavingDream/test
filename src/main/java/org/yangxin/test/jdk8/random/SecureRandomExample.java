package org.yangxin.test.jdk8.random;

import java.security.SecureRandom;

/**
 * @author yangxin
 * 2024/7/5 10:17
 */
public class SecureRandomExample {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        SecureRandom secureRandom = new SecureRandom();

        int randomInt = secureRandom.nextInt();
        double randomDouble = secureRandom.nextDouble();
        boolean randomBoolean = secureRandom.nextBoolean();

        System.out.println("Secure random int: " + randomInt);
        System.out.println("Secure random double: " + randomDouble);
        System.out.println("Secure random boolean: " + randomBoolean);
    }
}
