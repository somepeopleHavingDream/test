package org.yangxin.test.reflect.param;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 反射，为对象字段设置默认值（包括父类），
 * 仅支持String和本类型的包装部分（Number的子类）。
 *
 * @author yangxin
 * 2021/3/6 下午2:48
 */
public class ParamProcessor {

    public static void applyDefaultValue(Object o) {
        Class<?> sourceClass = o.getClass();

        // 索取对象所有字段，包括父类
        List<Field> fieldList = new ArrayList<>();
        while (sourceClass != null) {
            fieldList.addAll(Arrays.asList(sourceClass.getDeclaredFields()));
            sourceClass = sourceClass.getSuperclass();
        }

        for (Field field : fieldList) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(ParamDefaultValue.class)) {
                try {
                    // 有默认值则直接跳过
                    Object val = field.get(o);
                    if (val != null) {
                        continue;
                    }

                    // 如果字段类型是原始数据类型，则跳过
                    Class<?> type = field.getType();
                    if (type.isPrimitive()) {
                        continue;
                    }

                    String defaultValue = field.getAnnotation(ParamDefaultValue.class).value();
                    if (String.class.isAssignableFrom(type)) {
                        field.set(o, defaultValue);
                    } else if (Number.class.isAssignableFrom(type)) {
                        if (Byte.class.isAssignableFrom(type)) {
                            field.set(o, Byte.valueOf(defaultValue));
                        } else if (Float.class.isAssignableFrom(type)) {
                            field.set(o, Float.valueOf(defaultValue));
                        } else if (Short.class.isAssignableFrom(type)) {
                            field.set(o, Short.valueOf(defaultValue));
                        } else if (Integer.class.isAssignableFrom(type)) {
                            field.set(o, Integer.valueOf(defaultValue));
                        } else if (Double.class.isAssignableFrom(type)) {
                            field.set(o, Double.valueOf(defaultValue));
                        } else if (Long.class.isAssignableFrom(type)) {
                            field.set(o, Long.valueOf(defaultValue));
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
