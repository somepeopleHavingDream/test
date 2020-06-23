package org.yangxin.test.jvm.classloading;

import java.io.IOException;
import java.io.InputStream;

/**
 * 不同的类加载器对instanceof关键字运算结果的影响
 * 类加载器与instanceof关键字演示
 *
 * @author yangxin
 * 2020/06/23 17:24
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myLoader = new ClassLoader() {

            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                System.out.println("fileName: " + fileName);
                InputStream inputStream = getClass().getResourceAsStream(fileName);
                if (inputStream == null) {
                    return super.loadClass(name);
                }

                try {
                    byte[] b = new byte[inputStream.available()];
                    inputStream.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object object = myLoader.loadClass("org.yangxin.test.jvm.classloading.ClassLoaderTest").newInstance();
        System.out.println(object.getClass());
        System.out.println(object instanceof org.yangxin.test.jvm.classloading.ClassLoaderTest);
    }
}
