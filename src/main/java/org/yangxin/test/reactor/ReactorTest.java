package org.yangxin.test.reactor;

import reactor.core.publisher.Flux;

public class ReactorTest {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    /**
     * 包装单个值
     */
    private static void test1() {
        // 将字符串 "Hello" 包装为 Flux
        Flux<String> flux = Flux.just("Hello");
        flux.subscribe(System.out::println); // 输出: Hello
    }

    /**
     * 包装多个值
     */
    private static void test2() {
        // 依次发射 1, 2, 3
        Flux<Integer> numbers = Flux.just(1, 2, 3);
        numbers.subscribe(n -> System.out.println("Received: " + n));
    }

    /**
     * 与其他操作符结合
     */
    private static void test3() {
        // 组合使用 map 和 filter
        Flux.just(10, 20, 30)
                .map(x -> x * 2)
                .filter(x -> x > 25)
                .subscribe(System.out::println);
    }
}
