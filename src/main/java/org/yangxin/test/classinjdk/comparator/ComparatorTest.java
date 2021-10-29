package org.yangxin.test.classinjdk.comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author yangxin
 * 2021/10/27 14:07
 */
public class ComparatorTest {

    public static void main(String[] args) {
        test1();
    }

    /**
     * 用比较器排序
     */
    private static void test1() {
        Employee e1 = new Employee("John", 25, 3000.0, 9922001L);
        Employee e2 = new Employee("Ace", 22, 2000.0, 5924001L);
        Employee e3 = new Employee("Keith", 35, 4000.0, 3924401L);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);

        employeeList.sort(Comparator.comparing(Employee::getName));
        System.out.println(employeeList);

        employeeList.sort(Comparator.comparing(Employee::getName).reversed());
        System.out.println(employeeList);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Employee {

        private String name;
        private Integer age;
        private Double salary;
        private Long mobile;
    }
}
