package org.yangxin.test.classinjdk.basictype;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * 字符串测试类
 *
 * @author yangxin
 * 2019/10/31 11:36
 */
public class StringTest {

    public static void main(String[] args) throws JsonProcessingException, UnsupportedEncodingException {
//        test3();
//        test4();
//        test7();
//        test8();
        test9();
    }

    private static void test9() {
        String s = "147dba2630d74228af63c97bbaf9eacd";
        System.out.println(s.replaceAll("(.{2})", ":$1").substring(1));
    }

    private static void test8() {
        String s = "esi1111111111111111111";
        System.out.println(org.apache.commons.lang.StringUtils.substring(s, 0, 3));
    }

    private static void test7() {
        List<String> list = Arrays.asList("篮球", "足球");
        System.out.println(org.apache.commons.lang.StringUtils.join(list, "-"));
    }

    protected static String test6(String location) {
        // 获得前缀结束的下一个位置
        int prefixEnd = location.indexOf(':') + 1;

        // 确定根路径结束的位置
        int rootDirEnd = location.length();
        while (rootDirEnd > prefixEnd && new AntPathMatcher().isPattern(location.substring(prefixEnd, rootDirEnd))) {
            rootDirEnd = location.lastIndexOf('/', rootDirEnd - 2) + 1;
        }
        if (rootDirEnd == 0) {
            rootDirEnd = prefixEnd;
        }
        return location.substring(0, rootDirEnd);
    }

    private static void test5() {
        String chinese = "xxxxx";
        System.out.println(chinese.codePointAt(3));
    }

    private static void test4() {
        /*
            0：补0
            4：宽度为4位
            d：向左对齐
         */
        System.out.printf("%04d%n", 123);
        System.out.printf("%-10s%n", "123");
        System.out.printf("%4s%n", "123");
    }

    private static void test3() throws UnsupportedEncodingException {
        String str = "张三";
        byte[] bytes = str.getBytes("GB2312");
        // 按指定的长度截取新的字符数组
        byte[] newBytes = Arrays.copyOfRange(bytes, 0, 10);
        // 将新的字符数组转化为字符串
        String newStr = new String(newBytes, "GB2312");
        System.out.println(newStr);
        System.out.println(newStr.trim());
        System.out.println(Hex.encodeHex(newBytes));
    }

    private static void test2() {
        String nameAttr = "made,in:china";
        String flag = ",:";
        String[] nameArray = StringUtils.tokenizeToStringArray(nameAttr, flag);
        System.out.println(Arrays.toString(nameArray));
    }

    private static void test1() {
        // 字节数组作为参数，解码字节数组，把字节数组转换成字符串
        byte[] arr1 = {97, 98, 99};
        String s2 = new String(arr1);
        System.out.println(s2);
    }
}
