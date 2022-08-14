package org.yangxin.test.jdk8.comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * @author yangxin
 * 2021/10/27 14:07
 */
@SuppressWarnings({"AlibabaRemoveCommentedCode"})
public class ComparatorTest {

    public static void main(String[] args) {
//        test1();
        test2();
//        test3();
    }

    /**
     * Comparator.naturalOrder()比较器对Comparable对象施加自然排序
     */
    private static void test3() {
        List<Integer> list1 = Arrays.asList(212, 324, 435, 566, 133, 100, 121);
        list1.sort(Comparator.naturalOrder());
        System.out.println(list1);

        List<String> list2 = Arrays.asList("Aman", "Kajal", "Joyita", "Das");
        System.out.println(list2);

        list2.sort(Comparator.naturalOrder());
        System.out.println(list2);
    }

    private static void test2() {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<>(3);
        map1.put("id", "1");
        map1.put("name", "张三");
        map1.put("score", 86.5);

        Map<String, Object> map2 = new HashMap<>(3);
        map2.put("id", "2");
        map2.put("name", "李四");
        map2.put("score", 90.0);

        Map<String, Object> map3 = new HashMap<>(3);
        map3.put("id", "3");
        map3.put("name", "王五");
//        map3.put("score", 70.5);

        list.add(map1);
        list.add(map2);
        list.add(map3);

        // 升序
//        list.sort(Comparator.comparing((Map<String, Object> map) -> (Double) map.get("score"),
//                Comparator.nullsLast(Comparator.naturalOrder())));

        // 降序
        list.sort(Comparator.comparing((Map<String, Object> map) -> (Double) map.get("score"),
                Comparator.nullsLast(Comparator.reverseOrder())));

        System.out.println(list);
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
