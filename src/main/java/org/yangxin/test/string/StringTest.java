package org.yangxin.test.string;

/**
 * 字符串测试类
 *
 * @author yangxin
 * 2019/10/31 11:36
 */
public class StringTest {

    public static void main(String[] args) {
//        String body = "<200,access_token:bearer 7fe528d5-a69d-4239-b3cd-1f25c2edea33,{Date=[Thu, 03 Sep 2020 05:58:55 GMT], Content-Type=[text/plain;charset=UTF-8], Content-Length=[56], Connection=[keep-alive], X-Content-Type-Options=[nosniff], X-XSS-Protection=[1; mode=block], Cache-Control=[no-cache, no-store, max-age=0, must-revalidate], Pragma=[no-cache], Expires=[0], X-Static=[transfer], X-Via-JSL=[b983f4f,-], Set-Cookie=[__jsluid_h=de13f9a3c0940785c14efbba8c278e62; max-age=31536000; path=/; HttpOnly], X-Cache=[bypass]}>";
        String body = "access_token:bearer 7fe528d5-a69d-4239-b3cd-1f25c2edea33";

        System.out.println(body.substring(body.indexOf(':') + 1));
    }
}
