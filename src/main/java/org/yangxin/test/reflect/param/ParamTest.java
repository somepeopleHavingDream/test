package org.yangxin.test.reflect.param;

/**
 * @author yangxin
 * 2021/3/6 下午3:18
 */
public class ParamTest {

    @ParamDefaultValue("key")
    private String key;

    @ParamDefaultValue("value")
    private String value;

    public static void main(String[] args) {
        ParamTest paramTest = new ParamTest();
        ParamProcessor.applyDefaultValue(paramTest);

        System.out.println(paramTest.key);
        System.out.println(paramTest.value);
    }
}
