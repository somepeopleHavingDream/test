package org.yangxin.test.jvm.classrunning;

/**
 * 重载方法匹配优先级
 *
 * @author yangxin
 * 2020/07/16 16:11
 */
public class Overload {

//    public static void sayHello(Object arg) {
//        System.out.println("hello object.");
//    }

//    public static void sayHello(int arg) {
//        System.out.println("hello int.");
//    }

//    public static void sayHello(long arg) {
//        System.out.println("hello long.");
//    }

//    public static void sayHello(Character arg) {
//        System.out.println("hello Character.");
//    }

//    public static void sayHello(char arg) {
//        System.out.println("hello char");
//    }

    public static void sayHello(char... arg) {
        System.out.println("hello char ...");
    }

//    public static void sayHello(Serializable arg) {
//        System.out.println("hello Serializable");
//    }

    public static void main(String[] args) {
        sayHello('a');
    }
}
