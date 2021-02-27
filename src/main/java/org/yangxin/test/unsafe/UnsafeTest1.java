package org.yangxin.test.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author yangxin
 * 2021/2/27 下午10:37
 */
public class UnsafeTest1 {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        // Internal reference
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);

        // This creates an instance of player class without any initialization
        Player player = (Player) unsafe.allocateInstance(Player.class);
        // Print 0
        System.out.println(player.getAge());

        // Let's now set age 45 to un-initialized object
        player.setAge(45);
        // Print 45
        System.out.println(player.getAge());
    }
}

class Player {

    private Integer age;

    private Player() {
        this.age = 50;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
