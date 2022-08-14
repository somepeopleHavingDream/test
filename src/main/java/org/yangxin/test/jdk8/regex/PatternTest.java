package org.yangxin.test.jdk8.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yangxin
 * 2021/12/3 14:05
 */
@SuppressWarnings({"CommentedOutCode", "DuplicatedCode", "RegExpUnnecessaryNonCapturingGroup", "AlibabaAvoidPatternCompileInMethod"})
public class PatternTest {

    public static void main(String[] args) {
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
//        test8();
        test9();
    }

    private static void test9() {
        Pattern pattern = Pattern.compile("\\$\\{(?<target>\\w+)}");
        Matcher matcher = pattern.matcher("${APP_1068}");
        while (matcher.find()) {
            System.out.println(matcher.group("target"));
        }
    }

    /**
     * 懒惰（非贪婪）
     */
    private static void test8() {
        String regex = "(\\d{1,2}?)(\\d{3,4})";

        String str = "61762828 176 2991 87321";
        System.out.println("文本：" + str);
        System.out.println("贪婪模式：" + regex);

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println("匹配结果：" + matcher.group());
        }
    }

    /**
     * 贪婪
     */
    private static void test7() {
        String str = "61762828 176 2991 871";

        String regex = "\\d{3,6}";
        System.out.println("文本：" + str);
        System.out.println("贪婪模式：" + regex);

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println("匹配结果：" + matcher.group(0));
        }
    }

    /**
     * 反向引用
     */
    private static void test6() {
        String str = "aabbbbgbddesddfiid";

        Pattern pattern = Pattern.compile("(\\w)\\1");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    /**
     * 非捕获组
     */
    private static void test5() {
        String str = "020-85653333";

        String regex = "(?:0\\d{2})-(\\d{8})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println("分组的个数有：" + matcher.groupCount());
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println("第" + i + "个分组为：" + matcher.group(i));
            }
        }
    }

    /**
     * 命名编号捕获组
     */
    private static void test4() {
        String str = "020-85653333";

        String regex = "(?<area>0\\d{2})-(?<number>\\d{8})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            System.out.println("分组的个数有：" + matcher.groupCount());
            System.out.println(matcher.group("area"));
            System.out.println(matcher.group("number"));
        }
    }

    /**
     * 数字编号捕获组
     */
    private static void test3() {
        String str = "020-85653333";

        String regex = "(0\\d{2})-(\\d{8})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            System.out.println("分组的个数有：" + matcher.groupCount());
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println("第" + i + "个分组为：" + matcher.group(i));
            }
        }
    }

    /**
     * 零宽断言
     */
    private static void test2() {
        // 正向先行断言（正前瞻）
//        String regex = "\\d+(?=</span>)";

        // 正向后行断言（正后顾）
        String regex = "(?<=<span class=\"read-count\">阅读数：)\\d+";

        String str = "<span class=\"read-count\">阅读数：641</span>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println("匹配结果：" + matcher.group());
        }
    }
}
