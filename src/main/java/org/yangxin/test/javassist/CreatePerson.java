package org.yangxin.test.javassist;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yangxin
 * 2021/4/6 9:39
 */
@SuppressWarnings("AlibabaRemoveCommentedCode")
public class CreatePerson {

    /**
     * 创建一个Person对象
     */
    public static void createPerson() throws NotFoundException, CannotCompileException, IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ClassPool classPool = ClassPool.getDefault();

        // 1 创建一个空类
        CtClass ctClass = classPool.makeClass("org.yangxin.test.javassist.Person");

        // 2 新增一个字段 private String name
        // 字段名为name
        CtField ctField = new CtField(classPool.get("java.lang.String"), "name", ctClass);
        // 访问级别是private
        ctField.setModifiers(Modifier.PRIVATE);
        // 初始值是"xiaoming"
        ctClass.addField(ctField, CtField.Initializer.constant("xiaoming"));

        // 3 生成getter、setter方法
        ctClass.addMethod(CtNewMethod.setter("setName", ctField));
        ctClass.addMethod(CtNewMethod.getter("getName", ctField));

        // 4 添加无参的构造方法
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
        ctConstructor.setBody("{name = \"xiaohong\";}");
        ctClass.addConstructor(ctConstructor);

        // 5 添加有参的构造方法
        ctConstructor = new CtConstructor(new CtClass[]{classPool.get("java.lang.String")}, ctClass);
        // $0=this / $1,$2,$3...代表方法参数
        ctConstructor.setBody("{$0.name = $1;}");
        ctClass.addConstructor(ctConstructor);

        // 6 创建一个名为printName方法，无参数，无返回值，输出name
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "printName", new CtClass[]{}, ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("{System.out.println(name);}");
        ctClass.addMethod(ctMethod);

        // 这里会将这个创建的类对象编译为.class文件
//        ctClass.writeFile("D:\\IdeaProjects\\test\\src\\main\\java");

        // 这里不写入文件，直接实例化
        Object person = ctClass.toClass().newInstance();
        // 设置值
        Method setName = person.getClass().getMethod("setName", String.class);
        setName.invoke(person, "cunhua");
        // 输出值
        Method execute = person.getClass().getMethod("printName");
        execute.invoke(person);
    }

    public static void main(String[] args) {
        try {
            createPerson();
        } catch (NotFoundException | IOException | CannotCompileException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}


