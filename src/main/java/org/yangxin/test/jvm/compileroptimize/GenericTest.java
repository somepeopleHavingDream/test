package org.yangxin.test.jvm.compileroptimize;

import java.util.HashMap;
import java.util.Map;

/**
 * 泛型擦除前的例子
 *
 * @author yangxin
 * 2020/07/20 10:19
 */
public class GenericTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("hello", "你好");
        map.put("how are you?", "吃了没？");
        System.out.println(map.get("hello"));
        System.out.println(map.get("how are you?"));
    }
}
