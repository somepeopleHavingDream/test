package org.yangxin.test.string;

/**
 * 字符串测试类
 *
 * @author yangxin
 * 2019/10/31 11:36
 */
public class StringTest {
    public static void main(String[] args) {
        int num = 12;
        String result = String.format("%04d", num);
        System.out.println(result);
    }
}
