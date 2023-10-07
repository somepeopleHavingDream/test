package org.yangxin.test.spring.bean.mutablepropertyvalues;

import lombok.Data;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author yangxin
 * 2023/10/7 17:25
 */
public class MutablePropertyValuesExample {

    public static void main(String[] args) {
        // 创建Spring上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 获取MyBean实例
        MyBean myBean = context.getBean(MyBean.class);

        // 打印属性值
        System.out.println(myBean.getPropertyValues().getPropertyValue("propertyName"));

        // 关闭Spring上下文
        context.close();
    }
}

@Data
class MyBean {

    private MutablePropertyValues propertyValues;
}

class AppConfig {

    @Bean
    public MyBean myBean() {
        // 创建MyBean实例并设置属性值
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("propertyName", "propertyValue");

        MyBean myBean = new MyBean();
        myBean.setPropertyValues(propertyValues);
        return myBean;
    }
}
