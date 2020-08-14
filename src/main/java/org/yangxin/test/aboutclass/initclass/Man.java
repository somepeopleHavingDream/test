package org.yangxin.test.aboutclass.initclass;

/**
 * @author yangxin
 * 2020/08/14 17:30
 */
public class Man extends People {

    private String name = "聂小倩";
    private Integer age = 18;

    public Man() {
//        super();
        System.out.println("Man: " + toString());
    }

    @Override
    public void initValue() {
        name = "yangxin";
        age = 22;
    }

    @Override
    public String toString() {
        return "Man{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
