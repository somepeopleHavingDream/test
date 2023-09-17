package org.yangxin.test.spring.bean.beanwrapper;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author yangxin
 * 2023/9/17 15:27
 */
@SuppressWarnings("DataFlowIssue")
public class Main {

    public static void main(String[] args) {
        // 创建一个Person对象
        Person person = new Person();

        // 创建BeanWrapper并将Person对象包装进去
        BeanWrapper wrapper = new BeanWrapperImpl(person);

        // 设置属性值
        wrapper.setPropertyValue("name", "John");
        wrapper.setPropertyValue("age", 30);

        // 获取属性值
        String name = (String) wrapper.getPropertyValue("name");
        int age = (int) wrapper.getPropertyValue("age");

        System.out.println(name);
        System.out.println(age);
    }
}
