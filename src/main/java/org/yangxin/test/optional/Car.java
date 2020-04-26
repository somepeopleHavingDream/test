package org.yangxin.test.optional;

import lombok.Getter;

import java.util.Optional;

/**
 * @author yangxin
 * 2020/04/26 13:48
 */
@Getter
public class Car {

    /**
     * 车可能进行了保险，也可能没有保险，所以将这个字段声明为Optional
     */
    private Optional<Insurance> insurance;
}
