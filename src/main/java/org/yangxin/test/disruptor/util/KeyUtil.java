package org.yangxin.test.disruptor.util;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

/**
 * @author yangxin
 * 2022/1/19 21:11
 */
public class KeyUtil {

    public static String generatorUuid() {
        TimeBasedGenerator generator = Generators.timeBasedGenerator();
        return generator.generate().toString();
    }

    public static void main(String[] args) {
        System.out.println(generatorUuid());
        System.out.println(generatorUuid());
    }
}
