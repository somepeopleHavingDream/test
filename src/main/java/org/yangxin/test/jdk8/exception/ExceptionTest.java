package org.yangxin.test.jdk8.exception;

/**
 * @author yangxin
 * 2024/3/21 14:43
 */
@SuppressWarnings("CallToPrintStackTrace")
public class ExceptionTest {

    public static void main(String[] args) {
        try {
            // 可能会引发异常的代码
            int result = divide(10, 0);
            System.out.println("Result: " + result); // 这一行不会执行
        } catch (ArithmeticException e) {
            // 捕获 ArithmeticException 异常
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static int divide(int a, int b) {
        return a / b; // 尝试除以 0
    }
}
