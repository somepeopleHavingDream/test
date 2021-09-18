package org.yangxin.test.reflect.reflect;

/**
 * @author yangxin
 * 2020/06/28 11:23
 */
@SuppressWarnings({"ConstantConditions", "InstantiationOfUtilityClass"})
public class ReflectTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        test1();
//        test2();
    }

    private static void test2() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        Class<?> reflectTargetClass = contextClassLoader.loadClass("org.yangxin.test.reflect.reflect.ReflectTest");

        /*
         说明线程上下文类加载器实例出来的对象并没有破坏双亲委派模型，
         因为通过线程上下文类加载器实例化的实例仍然是ReflectTarget类的实例（o instanceof ReflectTarget返回真）。
         但线程上下文类加载器加载类这个行为本身是破坏了双亲委派模型，因为按照双亲委派模型来说，加载一个类的动作是由父类加载器来执行的，
         但是现在父类加载器把加载类的动作交给了子类加载器，因此这破坏了双亲委派模型，
         但正如上面所述，这并不影响线程上下文类加载器实例化出来的对象的使用。
        */
        Object o = reflectTargetClass.newInstance();
        System.out.println(o instanceof ReflectTest);
    }

    private static void test1() {
        // 第一种方式获取Class对象
        ReflectTest reflectTest = new ReflectTest();
        Class<? extends ReflectTest> reflectTargetClass1 = reflectTest.getClass();
        System.out.println("aClass: " + reflectTargetClass1.getName());

        // 第二种方式获取Class对象
        Class<ReflectTest> reflectTargetClass2 = ReflectTest.class;
        System.out.println("reflectTargetClass2: " + reflectTargetClass2.getName());

        // 判断第一种方式获取的class对象和第二种方式获取的是否是同一个
        System.out.println(reflectTargetClass1 == reflectTargetClass2);

        // 第三种方式来获取Class对象
        try {
            Class<?> reflectTargetClass3 = Class.forName("org.yangxin.test.reflect.reflect.ReflectTest");
            System.out.println("reflectTargetClass3: " + reflectTargetClass3.getName());
            System.out.println(reflectTargetClass2 == reflectTargetClass3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
