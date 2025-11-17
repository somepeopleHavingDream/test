package org.yangxin.test.reactor;

import lombok.SneakyThrows;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("CommentedOutCode")
public class FluxTest {
    public static void main(String[] args) {
        test1();
//        test2();
//        test3();
//        test4();
    }

    private static void test4() {
        List<String> list = Arrays.asList("A", "B", "C");
        Flux<String> flux = Flux.fromIterable(list);
        flux.subscribe(System.out::println);
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
    @SneakyThrows
    private static void test2() {
        // 依次发射 1, 2, 3
        Flux<Integer> numbers = Flux.just(1, 2, 3);
        numbers.flatMap(i -> Mono.just(i * 10)
//        numbers.flatMapSequential(i -> Mono.just(i * 10)
                        .delayElement(Duration.ofMillis(200 - i * 50)))
                .subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(5);
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
