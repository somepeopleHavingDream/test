package org.yangxin.test.javassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

/**
 * @author yangxin
 * 2021/4/6 10:24
 */
public class CreatePersonI {

    public static void createPersonI() throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        ClassPool classPool = ClassPool.getDefault();
        classPool.appendClassPath("D:\\IdeaProjects\\test\\src\\main\\java");

        // 获取接口
        CtClass ctClassI = classPool.get("org.yangxin.test.javassist.IPerson");
        // 获取上面生成的类
        CtClass ctClass = classPool.get("org.yangxin.test.javassist.Person");
        // 使代码生成的类，实现PersonI接口
        ctClass.setInterfaces(new CtClass[]{ctClassI});

        // 以下通过接口直接调用，强转
        IPerson iPerson = (IPerson) ctClass.toClass().newInstance();
        System.out.println(iPerson.getName());
        iPerson.setName("xiaolv");
        iPerson.printName();
    }

    public static void main(String[] args) {
        try {
            createPersonI();
        } catch (NotFoundException | CannotCompileException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
