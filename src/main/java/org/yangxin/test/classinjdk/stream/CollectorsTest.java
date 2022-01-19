package org.yangxin.test.classinjdk.stream;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author yangxin
 * 2022/1/19 9:19
 */
@SuppressWarnings({"CommentedOutCode", "SimplifyStreamApiCallChains"})
public class CollectorsTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }

    private static void test4() {
        List<String> list = Arrays.asList("123", "456", "789");
        Integer result = list.stream().collect(Collectors.collectingAndThen(Collectors.toList(), List::size));
        System.out.println(result);
    }

    private static void test3() {
        List<String> list = Arrays.asList("123", "456", "789");
        System.out.println(list.stream().collect(Collectors.mapping(Integer::valueOf, Collectors.toList())));
    }

    private static void test2() {
        List<String> list = Arrays.asList("A", "B", "C", "D");
        System.out.println(list.stream().collect(Collectors.joining("-", "Begin", "End")));
    }

    /**
     * 分组
     */
    private static void test1() {
        Product product1 = new Product(1L, 1, new BigDecimal("15.5"), "面包", "零食");
        Product product2 = new Product(2L, 2, new BigDecimal("20"), "饼干", "零食");
        Product product3 = new Product(3L, 3, new BigDecimal("30"), "月饼", "零食");
        Product product4 = new Product(4L, 3, new BigDecimal("10"), "青岛啤酒", "啤酒");
        Product product5 = new Product(5L, 10, new BigDecimal("15"), "百威啤酒", "啤酒");

        List<Product> productList = Lists.newArrayList(product1, product2, product3, product4, product5);

        // 按照类目分组
        Map<String, List<Product>> map1 = productList.stream().collect(Collectors.groupingBy(Product::getCategory));
        System.out.println(map1);

        // 按照几个属性拼接分组
        Map<String, List<Product>> map2 = productList.stream().collect(Collectors.groupingBy(item
                -> item.getCategory() + "_" + item.getName()));
        System.out.println(map2);

        // 根据不同条件分组
        Map<String, List<Product>> map3 = productList.stream().collect(Collectors.groupingBy(item
                -> item.getNum() < 3 ? "3" : "other"));
        System.out.println(map3);

        // 分级分组
        Map<String, Map<String, List<Product>>> map4 = productList.stream().collect(Collectors.groupingBy(Product::getCategory,
                Collectors.groupingBy(item -> item.getNum() < 3 ? "3" : "other")));
        System.out.println(map4);

        /*
            按子组收集数据
         */

        // 求总数
        Map<String, Long> map5 = productList.stream().collect(Collectors.groupingBy(Product::getCategory,
                Collectors.counting()));
        System.out.println(map5);

        // 求和
        Map<String, Integer> map6 = productList.stream().collect(Collectors.groupingBy(Product::getCategory,
                Collectors.summingInt(Product::getNum)));
        System.out.println(map6);

        // 把收集器的结果转换为另一种类型
        Map<String, Product> map7 = productList.stream().collect(Collectors.toMap(Product::getCategory,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparingInt(Product::getNum))));
        System.out.println(map7);

        // 联合其他收集器
        Map<String, Set<String>> map8 = productList.stream().collect(Collectors.groupingBy(Product::getCategory,
                Collectors.mapping(Product::getName, Collectors.toSet())));
        System.out.println(map8);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private static class Product implements Serializable {

        private static final long serialVersionUID = -4056087910801132938L;

        private Long id;
        private Integer num;
        private BigDecimal price;
        private String name;
        private String category;
    }
}
