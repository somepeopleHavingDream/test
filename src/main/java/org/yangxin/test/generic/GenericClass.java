package org.yangxin.test.generic;

import lombok.Data;

/**
 * 1：把泛型定义在类上
 * 2：类型变量定义在类上，方法中也可以使用
 *
 * @author yangxin
 * 2020/05/09 14:41
 */
@Data
public class GenericClass<T> {

    private T obj;

    public static void main(String[] args) {
        // 创建对象并指定元素类型
        GenericClass<String> tool = new GenericClass<>();

        tool.setObj("钟福成");
        String s = tool.getObj();
        System.out.println(s);

        // 创建对象并指定元素类型
        GenericClass<Integer> genericClass = new GenericClass<>();
        // 如果我在这个对象里传入的是String类型的，它在编译时期就通过不了
        genericClass.setObj(10);
        Integer obj = genericClass.getObj();
        System.out.println(obj);
    }
}
