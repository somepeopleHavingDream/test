package org.yangxin.test.classinjdk.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author yangxin
 * 2021/2/27 下午11:00
 */
public class UnsafeTest2 {

    private static Unsafe unsafe;

    static {
        try {
            Field getUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            getUnsafe.setAccessible(true);
            unsafe = (Unsafe) getUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        opsArray();
    }

    private static void opsArray() {
    /*
        操作数组：
        可以获取数组的在内容中的基本偏移量（arrayBaseOffset），
        获取数组内元素的间隔（比例），
        根据数组对象和偏移量获取元素值（getObject），
        设置数组元素值（putObject），
        示例如下。
     */
        String[] stringArr = {"Alice", "Bob", "Cindy"};
//        String[] stringArr = {"1", "2", "3"};
        long arrayBaseOffset = unsafe.arrayBaseOffset(String[].class);
        System.out.println("arrayBaseOffset: " + arrayBaseOffset);

        // every index scale
        long scale = unsafe.arrayIndexScale(String[].class);
        System.out.println("scale: " + scale);

        // print first string in strings[]
        System.out.println("first element is: " + unsafe.getObject(stringArr, arrayBaseOffset));

        // set 100 to first string
        unsafe.putObject(stringArr, arrayBaseOffset, "100");

        // print first string in strings[] again
        System.out.println("after set, first element is: " + unsafe.getObject(stringArr, arrayBaseOffset));
    }
}
