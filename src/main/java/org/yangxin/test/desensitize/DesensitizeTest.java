package org.yangxin.test.desensitize;

/**
 * @author yangxin
 * 2021/12/8 16:38
 */
public class DesensitizeTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        String phone = "wangpc111111";
//        String phone = "18742539898";
        System.out.println(phone.replaceAll("(\\w{3})\\w*(\\w{4})", "$1****$2"));
    }
}
