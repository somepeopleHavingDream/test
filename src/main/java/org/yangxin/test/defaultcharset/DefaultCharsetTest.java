package org.yangxin.test.defaultcharset;

import java.nio.charset.Charset;

/**
 * Java获取系统中默认的编码
 *
 * @author yangxin
 * 2020/09/29 09:17
 */
public class DefaultCharsetTest {

    public static void main(String[] args) {
        // 获取系统默认编码
        System.out.println(System.getProperty("file.encoding"));
        // 获取系统默认的字符编码
        System.out.println(Charset.defaultCharset());
        // 获取系统默认语言
        System.out.println(System.getProperty("user.language"));
        // 操作系统
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.arch"));
        System.out.println(System.getProperty("os.version"));
        // 获取系统属性列表
        System.getProperties().list(System.out);
        // 设置编码
//        System.getProperties().put("file.encoding", "GBK");
    }
}
