package org.yangxin.test.reflect.param;

import org.springframework.core.annotation.AnnotationUtils;

/**
 * @author yangxin
 * 2021/3/6 下午3:18
 */
@ParamDefaultValue("key")
public class ParamTest {

    @ParamDefaultValue("key")
    private String key;

    @ParamDefaultValue("value")
    private String value;

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        ParamDefaultValue annotation = AnnotationUtils.findAnnotation(ParamTest.class, ParamDefaultValue.class);
        System.out.println(annotation);
    }

    private static void test1() {
        ParamTest paramTest = new ParamTest();
        ParamProcessor.applyDefaultValue(paramTest);

        System.out.println(paramTest.key);
        System.out.println(paramTest.value);
    }
}
