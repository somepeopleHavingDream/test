package org.yangxin.test.jvm.classloading;

/**
 * 对于初始化阶段，虚拟机规范则是严格规定了有且只有四种情况必须立即对类进行“初始化”（而加载、验证、准备自然需要在此之前开始）
 * 1）遇到new、getstatic、putstatic或invokestatic这4条字节码指令时，如果类没有进行初始化，则需要先触发其初始化。
 * 生成这4条指令的最常见的Java代码场景是：使用new关键字实例化对象的时候，读取或设置一个类的静态字段（被final修饰、已在编译器把结果放入常量池的
 * 静态字段除外）的时候，以及调用一个类的静态方法的时候。
 * 2）使用java.lang.reflect包的方法对类进行反射调用的时候，如果类没有进行过初始化，则需要先触发其初始化
 * 3）当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化
 * 4）当虚拟机启动时，用户需要指定一个要执行的主类（包含main()方法的那个类），虚拟机会先初始化这个主类
 *
 * 接口与类真正有所区别的是前面讲述的四种“有且仅有”需要开始初始化场景中的第三种：
 * 当一个类在初始化时，要求其父类全部都已经初始化过了，但是一个接口在初始化时，并不要求其父接口全部都完成初始化，
 * 只有在真正使用到父接口的时候（如引用接口中定义的常量）才会初始化
 *
 * 被动使用类字段演示一：
 * 通过子类引用父类的静态字段，不会导致子类初始化
 *
 * @author yangxin
 * 2020/06/18 17:50
 */
public class SuperClass {

    static {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;
}
