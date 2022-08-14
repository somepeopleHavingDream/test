package org.yangxin.test.jdk8.url;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author yangxin
 * 2022/2/18 11:18
 */
public class UrlTest {

    public static void main(String[] args) throws MalformedURLException {
        test1();
    }

    private static void test1() throws MalformedURLException {
//        URL url = new URL("D:\\IdeaProjects\\spring-framework-5.2.9.RELEASE\\spring-demo\\src\\main\\resources\\spring\\spring-config.xml");
        URL url = new URL("http://www.runoob.com/index.html?language=cn#j2se");
        System.out.println("url为：" + url);
        System.out.println("协议为：" + url.getProtocol());
        System.out.println("验证信息：" + url.getAuthority());
        System.out.println("文件名及请求参数：" + url.getFile());
        System.out.println("主机名：" + url.getHost());
        System.out.println("路径：" + url.getPath());
        System.out.println("端口：" + url.getPort());
        System.out.println("默认端口：" + url.getDefaultPort());
        System.out.println("请求参数：" + url.getQuery());
        System.out.println("定位位置：" + url.getRef());
    }
}
