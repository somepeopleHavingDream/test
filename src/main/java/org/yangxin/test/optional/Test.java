package org.yangxin.test.optional;

import java.util.Optional;

/**
 * @author yangxin
 * 2020/04/26 13:50
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(getCarInsuranceName(null));
    }

    public static String getCarInsuranceName(Person person) {
        Optional<Person> optionalPerson = Optional.of(person);
        return optionalPerson.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                // 如果Optional的结果值为空，设置默认值
                .orElse("Unknown");
    }
}
