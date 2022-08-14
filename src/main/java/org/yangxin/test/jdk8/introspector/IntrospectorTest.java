package org.yangxin.test.jdk8.introspector;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author yangxin
 * 2022/3/28 11:31
 */
public class IntrospectorTest {

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
//        test1();
//        test2();
        test3();
    }

    private static void test3() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        User user = new User("jack", 21);

        String propertyName = "name";
        PropertyDescriptor namePd = new PropertyDescriptor(propertyName, User.class);

        System.out.println("名字：" + namePd.getReadMethod().invoke(user));
        namePd.getWriteMethod().invoke(user, "tom");
        System.out.println("名字：" + namePd.getReadMethod().invoke(user));
    }

    private static void test2() throws IntrospectionException {
        /*
            获取整个bean的信息，
            在Object类时停止检索，可以选择在任意一个父类停止
         */
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);

        // 获取所有属性描述
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            System.out.println(propertyDescriptor.getName());
        }

        // 获取所有方法描述
        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        for (MethodDescriptor methodDescriptor : methodDescriptors) {
            System.out.println(methodDescriptor.getName());
        }
    }

    private static void test1() {
        System.out.println(Introspector.decapitalize("IntrospectorTest"));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {

        private String name;
        private Integer age;
    }
}
