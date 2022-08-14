package org.yangxin.test.jdk8.collator;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

/**
 * @author yangxin
 * 2022/6/6 22:12
 */
public class CollatorTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        String[] names = {"王林", "杨宝", "李镇", "刘迪", "刘波"};
        Arrays.sort(names, Collator.getInstance(Locale.CHINA));

        // 升序
        System.out.println(Arrays.toString(names));
    }
}
