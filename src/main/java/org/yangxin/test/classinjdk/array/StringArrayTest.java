package org.yangxin.test.classinjdk.array;

/**
 * @author yangxin
 * 2021/12/1 10:31
 */
@SuppressWarnings({"MismatchedReadAndWriteOfArray", "ConstantConditions"})
public class StringArrayTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        String[][] array = new String[2][3];
//        array[3][0] = "1";
        array[2][3] = "2";
    }
}
