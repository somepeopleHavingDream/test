package org.yangxin.test.jvm.classloading;

import sun.misc.Launcher;

import java.net.URL;

/**
 * 不同的类加载器对instanceof关键字运算结果的影响
 * 类加载器与instanceof关键字演示
 *
 * @author yangxin
 * 2020/06/23 17:24
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        printJDKJar();
//        ClassLoader myLoader = new ClassLoader() {
//
//            @SuppressWarnings("ResultOfMethodCallIgnored")
//            @Override
//            public Class<?> loadClass(String name) throws ClassNotFoundException {
//                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
//                System.out.println("fileName: " + fileName);
//                InputStream inputStream = getClass().getResourceAsStream(fileName);
//                if (inputStream == null) {
//                    return super.loadClass(name);
//                }
//
//                try {
//                    byte[] b = new byte[inputStream.available()];
//                    inputStream.read(b);
//                    return defineClass(name, b, 0, b.length);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    throw new ClassNotFoundException(name);
//                }
//            }
//        };
//
//        Object object = myLoader.loadClass("org.yangxin.test.jvm.classloading.ClassLoaderTest").newInstance();
//        System.out.println(object.getClass());
//        System.out.println(object instanceof org.yangxin.test.jvm.classloading.ClassLoaderTest);
    }

    /**
     * 获取根类加载器所加载的核心类库，并会看到本机安装的Java环境变量指定的JDK中提供的核心jar包路径
     */
    private static void printJDKJar() {
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.toExternalForm());
        }
    }
}
