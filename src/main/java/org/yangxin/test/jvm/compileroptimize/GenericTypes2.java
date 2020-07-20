package org.yangxin.test.jvm.compileroptimize;

import java.util.ArrayList;
import java.util.List;

/**
 * 当泛型遇见重载2
 *
 * @author yangxin
 * 2020/07/20 10:27
 */
@Deprecated
public class GenericTypes2 {

//    public static String method(List<String>list) {
//        System.out.println("invoke method(List<String> list");
//        return "";
//    }

    @SuppressWarnings("UnusedReturnValue")
    public static int method(List<Integer> list) {
        System.out.println("invoke method(List<Integer> list)");
        return 1;
    }

    @SuppressWarnings("Convert2Diamond")
    public static void main(String[] args) {
//        method(new ArrayList<String>());
        method(new ArrayList<Integer>());
    }
}
