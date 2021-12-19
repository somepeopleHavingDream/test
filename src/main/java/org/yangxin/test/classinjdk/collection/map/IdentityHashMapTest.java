package org.yangxin.test.classinjdk.collection.map;

import java.awt.*;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author yangxin
 * 2021/9/22 10:13
 */
public class IdentityHashMapTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        Map<Twins, String> hashMap = new HashMap<>();
        Map<Twins, String> identityMap = new IdentityHashMap<>();

        // 兄弟
        Twins brother = new Twins(Color.GREEN);
        // 哥哥
        Twins eldBrother = new Twins(Color.GREEN);

        hashMap.put(brother, "弟弟");
        hashMap.put(eldBrother, "哥哥");

        // 结果却只有一个
        System.out.println(hashMap);

        identityMap.put(brother, "绿色衣服的弟弟");
        // 第二天弟弟换了一身蓝衣服
        brother.setColor(Color.BLUE);
        identityMap.put(brother, "蓝色衣服的弟弟");
        System.out.println(identityMap);
    }

    private static void test1() {
        Map<String, String> map = new IdentityHashMap<>();
        map.put(new String("a"), "1");
        map.put(new String("a"), "2");
        map.put(new String("a"), "3");
        System.out.println(map.size());
        System.out.println(map.get("a"));
        System.out.println(map.get(new String("a")));
    }
}

class Twins {

    /**
     * 衣服颜色
     */
    private Color color;

    public Twins(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Twins)) {
            return false;
        }

        Twins twins = (Twins) o;
        return color.equals(twins.color);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + color.hashCode();
        return result;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
