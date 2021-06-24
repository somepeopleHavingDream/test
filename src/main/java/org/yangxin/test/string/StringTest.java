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
//        testCodePointAt();

//        String location = "D:\\IdeaProjects\\spring-framework-5.2.9.RELEASE\\spring-demo\\src\\main\\resources\\spring\\spring-config.xml";
//        System.out.println(determineRootDir(location));

        testTokenizeToStringArray();
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
