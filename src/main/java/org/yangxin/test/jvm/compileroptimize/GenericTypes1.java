package org.yangxin.test.jvm.compileroptimize;

import java.util.List;

/**
 * 当泛型遇见重载1
 *
 * @author yangxin
 * 2020/07/20 10:23
 */
public class GenericTypes1 {

//    public static void method(List<String> list) {
//        System.out.println("invoke method(List<String> list)");
//    }

    public static void method(List<Integer> list) {
        System.out.println("invoke method(List<Integer> list");
    }
}
