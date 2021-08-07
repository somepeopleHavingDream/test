package org.yangxin.test.datatype;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * 字符串测试类
 *
 * @author yangxin
 * 2019/10/31 11:36
 */
@SuppressWarnings("CommentedOutCode")
public class StringTest {

    public static void main(String[] args) throws JsonProcessingException, UnsupportedEncodingException {
//        testTokenizeToStringArray();
//        test1();
//        test2();
        test3();
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
        byte[] bytes = new byte[]{0x38, 0x36, 0x39, 0x35, 0x32, 0x33, 0x30, 0x35, 0x30, 0x34 ,0x35, 0x37, 0x35, 0x35, 0x36, 0x20, 0x20, 0x20, 0x20};
        String s = new String(bytes);
        System.out.println(s);
    }

    private static void test1() {
        // 字节数组作为参数，解码字节数组，把字节数组转换成字符串
        byte[] arr1 = {97, 98, 99};
        String s2 = new String(arr1);
        System.out.println(s2);
    }

    private static void testTokenizeToStringArray() {
        String nameAttr = "made,in:china";
        String flag = ",:";
        String[] nameArray = StringUtils.tokenizeToStringArray(nameAttr, flag);
        System.out.println(Arrays.toString(nameArray));
    }

    private static void testCodePointAt() {
        String chinese = "xxxxx";
        System.out.println(chinese.codePointAt(3));
    }

    protected static String determineRootDir(String location) {
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
}
