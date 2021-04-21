package org.yangxin.test.classloader;

import org.apache.commons.lang.StringUtils;

/**
 * @author yangxin
 * 2021/4/21 17:19
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        // 查看当前系统类路径中包含的路径条目
        String classPath = System.getProperty("java.class.path");
        for (String path : StringUtils.splitByWholeSeparatorPreserveAllTokens(classPath, ";")) {
            System.out.println(path);
        }
        System.out.println(classPath);
        // 调用加载当前类的类加载器（这里即为系统类加载器）加载TestBean
        try {
            Class<?> typeLoaded = Class.forName("org.yangxin.test.classloader.TestBean");
            // 查看被加载的TestBean类型是被哪个类加载器加载的
            System.out.println(typeLoaded.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class TestBean {

    public TestBean() {}
}