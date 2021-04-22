package org.yangxin.test.classloader;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;

/**
 * @author yangxin
 * 2021/4/21 17:19
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        // 查看当前系统类路径中包含的路径条目
        String classpath = System.getProperty("java.class.path");

        final String pathSeparator = ";";
        for (String path : StringUtils.splitByWholeSeparatorPreserveAllTokens(classpath, pathSeparator)) {
            System.out.println(path);
        }

        System.out.println("java.class.path: " + classpath);
        System.out.println("java.ext.dirs: " + System.getProperty("java.ext.dirs"));
        System.out.println("sun.boot.class.path: " + System.getProperty("sun.boot.class.path"));

        // 调用加载当前类的类加载器（这里即为系统类加载器）加载TestBean
        try {
            Class<?> typeLoaded = Class.forName("org.yangxin.test.classloader.TestBean");
            // 查看被加载的TestBean类型是被哪个类加载器加载的
            System.out.println(typeLoaded.getClassLoader());
            System.out.println(HashMap.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class TestBean {

    public TestBean() {}
}