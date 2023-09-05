package org.yangxin.test.jdk8.annotation.repeatingannotation;

import java.util.Objects;

/**
 * @author yangxin
 * 2023/9/5 14:11
 */
public class Main {

    public static void main(String[] args) {
        Class<MyClass> myClass = MyClass.class;

        // 获取包含@Tags注解的容器注解
        Tags tags = myClass.getAnnotation(Tags.class);

        // 获取@Tag注解数组
        if (Objects.nonNull(tags)) {
            Tag[] tagArray = tags.value();
            for (Tag tag : tagArray) {
                System.out.println(tag.value());
            }
        }
    }
}
