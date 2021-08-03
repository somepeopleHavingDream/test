package org.yangxin.test.string;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * 字符串测试类
 *
 * @author yangxin
 * 2019/10/31 11:36
 */
public class StringTest {

    public static void main(String[] args) throws JsonProcessingException {
//        testTokenizeToStringArray();
        test1();
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
