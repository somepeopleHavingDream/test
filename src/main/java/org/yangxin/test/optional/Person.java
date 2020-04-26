package org.yangxin.test.optional;

import lombok.Getter;

import java.util.Optional;

/**
 * @author yangxin
 * 2020/04/26 13:47
 */
@Getter
public class Person {

    /**
     * 人可能有车，也可能没有车，因此将这个字段声明为Optional
     */
    private Optional<Car> car;
}
