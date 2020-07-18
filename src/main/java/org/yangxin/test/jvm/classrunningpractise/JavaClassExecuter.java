package org.yangxin.test.jvm.classrunningpractise;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * JavaClass执行工具
 *
 * @author yangxin
 * 2020/07/17 17:35
 */
@Deprecated
public class JavaClassExecuter {

    /**
     * 执行外部传过来的代表一个Java类的Byte数组
     * 将输入类的byte数组中代表java.lang.System的CONSTANT_UTF8_INFO常量修改为劫持后的HackSystem类
     * 执行方法为该类的static main(String[] args)方法，输出结果为该类向System.out/err输出的信息
     *
     * @param classByte 代表一个Java类的Byte数组
     * @return 执行结果
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static String execute(byte[] classByte) {
        HackSystem.clearBuffer();
        ClassModifier cm = new ClassModifier(classByte);
        byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "org/yangxin/test/jvm/classrunningpractise/HackSystem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class clazz = loader.loadByte(modiBytes);
        try {
            Method method = clazz.getMethod("main", String[].class);
            method.invoke(null, (Object) new String[]{null});
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return HackSystem.getBufferString();
    }
}
