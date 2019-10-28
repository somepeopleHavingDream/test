package org.yangxin.test.stringbuilder;

/**
 * 用StringBuilder前补0
 *
 * @author yangxin
 * 2019/10/28 11:55
 */
public class StringBuilderTest {
    public static void main(String[] args) {
        String s = "110";
        StringBuilder builder = new StringBuilder(s);

        // 往前补4个0
        for (int i = 0; i < 4; i++) {
            builder.insert(0, '0');
        }
        System.out.println(builder.toString());
    }
}
