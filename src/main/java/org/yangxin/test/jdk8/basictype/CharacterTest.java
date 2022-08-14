package org.yangxin.test.jdk8.basictype;

/**
 * @author yangxin
 * 2021/3/6 下午2:12
 */
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class CharacterTest {

    public static void main(String[] args) {
        // create and assign values to int codepoint cp
        int cp = 0x12345;

        // create an int res
        int res;

        // assign the result of charCount on cp to res
        res = Character.charCount(cp);

        String str1 = "It is not a valid supplementary character";
        String str2 = "It is a valid supplementary character";

        // print res value
        if (res == 1) {
            System.out.println(str1);
        } else if (res == 2) {
            System.out.println(str2);
        }
    }
}
