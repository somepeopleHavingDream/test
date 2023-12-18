package org.yangxin.test.jdk8.thread;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * @author yangxin
 * 2021/8/26 17:26
 */
@SuppressWarnings({"divzero", "NumericOverflow", "AlibabaUndefineMagicConstant", "CommentedOutCode", "DuplicatedCode", "unused", "AlibabaRemoveCommentedCode", "CallToPrintStackTrace"})
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1();

//        runAsync();
//        supplyAsync();
        whenComplete();
//        thenApply();
//        handle();
//        thenAccept();
//        thenRun();
//        thenCombine();
//        thenAcceptBoth();
//        applyToEither();
//        acceptEither();
//        runAfterEither();
//        runAfterBoth();
//        thenCompose();
//        allOf();
//        complete();
    }

    private static void complete() {
        CompletableFuture<Integer> future = new CompletableFuture<>();

        // 模拟异步计算
        new Thread(() -> {
            try {
                // 模拟计算过程
                Thread.sleep(2000);

                // 手动设置结果
                future.complete(42);
            } catch (InterruptedException e) {
                // 处理异常
                future.completeExceptionally(e);
            }
        }).start();

        // 在此处可以继续其他操作
        System.out.println("Waiting for result..");

        // 获取结果（会等待计算完成）
        Integer result = future.join();
        System.out.println(result);
    }

    private static void allOf() {
        // 记录开始时间
        long start = System.currentTimeMillis();

        // 定长线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 任务
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<String> resultList = new ArrayList<>();
        Map<String, String> errorList = new HashMap<>();

        Stream<CompletableFuture<String>> completableFutureStream = integerList.stream()
                .map(num -> CompletableFuture.supplyAsync(() -> getDouble(num), executorService)
                        .handle((integer, throwable) -> {
                            if (Objects.isNull(throwable)) {
                                System.out.println("任务" + num + "完成！ result=" + integer + "，" + new Date());
                                resultList.add(integer.toString());
                            } else {
                                System.out.println("任务" + num + "异常！ e=" + throwable + "，" + new Date());
                                errorList.put(num.toString(), throwable.getMessage());
                            }

                            return "";
                        }));

        CompletableFuture<?>[] futureArray = completableFutureStream.toArray(CompletableFuture[]::new);
        Void join = CompletableFuture.allOf(futureArray)
                .whenComplete(((v, th) -> System.out.println("所有任务执行完成触发\n resultList=" + resultList
                        + "\n errorList=" + errorList
                        + "\n耗时=" + (System.currentTimeMillis() - start))))
                .join();
        executorService.shutdown();
    }

    /**
     * 根据数字判断线程休眠的时间
     *
     * @param i 数字
     * @return 线程休眠的时间
     */
    public static Integer getDouble(Integer i) {
        try {
            if (i == 1) {
                // 任务1耗时3秒
                TimeUnit.SECONDS.sleep(3);
            } else if (i == 2) {
                // 任务2耗时1秒，还出错
                TimeUnit.SECONDS.sleep(1);
                throw new RuntimeException("出异常了");
            } else {
                // 其他任务耗时1秒
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2 * i;
    }

    /*
        1、runAsync和supplyAsync方法
        CompletableFuture提供了四个静态方法来创建一个异步操作。
     */

    /**
     * 无返回值
     */
    private static void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {
            }

            System.out.println("run end ...");
        });

        System.out.println(future.get());
    }

    /**
     * 无返回值
     */
    private static void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = null;
        for (int i = 0; i < 12; i++) {
            future = CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
                } catch (InterruptedException ignored) {
                }

                System.out.println("run end ...");
            });
        }

        System.out.println(future.get());
    }

    /**
     * 有返回值
     */
    private static void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {
            }

            System.out.println("run end ...");
            return System.currentTimeMillis();
        });

        Long time = future.get();
        System.out.println("time = " + time);
    }

    /*
        2、计算结果完成时的回调方法
        当CompletableFuture的计算结果完成，或者抛出异常时，可以执行特定的Action。
     */

    private static void whenComplete() throws InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {
            }

            if (new Random().nextInt() % 2 >= 0) {
                int i = 12 / 0;
            }

            System.out.println("run end ...");
        });

//        future.whenComplete((unused, throwable) -> throwable.printStackTrace());
        future.whenComplete((unused, throwable) -> System.out.println("执行完成！"));

        future.exceptionally(throwable -> {
            System.out.println("执行失败！" + throwable.getMessage());
            throwable.printStackTrace();
//            System.out.println("执行失败！" + throwable.getMessage());
            return null;
        });

        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
//        TimeUnit.SECONDS.sleep(2);
    }

    /*
        3、thenApply方法
        当一个线程依赖另一个线程时，可以使用thenApply方法来把这两个线程串行化。
     */

    private static void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            long result = new Random().nextInt(100);
            System.out.println("result1 = " + result);
            return result;
        }).thenApply(t -> {
            long result = t * 5;
            System.out.println("result2 = " + result);
            return result;
        });

        Long result = future.get();
        System.out.println(result);
    }

    /*
        4、handle方法
        handle是执行任务完成时对结果的处理。
     */

    private static void handle() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 10 / 0;
            return new Random().nextInt(10);
        }).handle((param, throwable) -> {
            int result = -1;
            if (throwable == null) {
                result = param * 2;
            } else {
                System.out.println(throwable.getMessage());
            }

            return result;
        });

        System.out.println(future.get());
    }

    /*
        5、thenAccept消费处理结果
        接收任务的处理结果，并消费处理，无返回结果
     */

    private static void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> new Random().nextInt(10))
                .thenAccept(System.out::println);
        future.get();
    }

    /*
        6、thenRun方法
        跟thenAccept方法不一样的是，不关心任务的处理结果，只要上面的任务执行完成，就开始执行thenRun。
     */

    private static void thenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> new Random().nextInt(10))
                .thenRun(() -> System.out.println("thenRun ..."));
        future.get();
    }

    /*
        7、thenCombine 合并任务
        thenCombine会把两个CompletionStage的任务都执行完成后，把两个任务的结果一块交给thenCombine来处理。
     */

    private static void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "hello");

        CompletableFuture<String> result = future1.thenCombine(future2, (t, u) -> t + " " + u);
        System.out.println(result.get());
    }

    /*
        8、thenAcceptBoth
        当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗。
     */

    private static void thenAcceptBoth() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);

            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("f1 = " + t);
            return t;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);

            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("f2 = " + t);
            return t;
        });

        CompletableFuture<Void> result = future1
                .thenAcceptBoth(future2, (t, u) -> System.out.println("f1 = " + t + " u = " + u));
        result.get();
    }

    /*
        9、applyToEither方法
        两个CompletionStage，谁执行返回的结果快，我就用哪个CompletionStage的结果进行下一步的转化操作。
     */

    private static void applyToEither() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);

            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("f1 = " + t);
            return t;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);

            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("f2 = " + t);
            return t;
        });

        CompletableFuture<Integer> result = future1.applyToEither(future2, t -> {
            System.out.println(t);
            return t * 2;
        });

        System.out.println(result.get());
    }

    /*
        10、acceptEither方法
        两个CompletionStage，谁执行返回的结果快，我就用哪个CompletionStage的结果进行下一步的消耗操作。
     */

    private static void acceptEither() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);

            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("f1 = " + t);
            return t;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);

            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("f2 = " + t);
            return t;
        });

        CompletableFuture<Void> result = future1.acceptEither(future2, System.out::println);
        result.get();
    }

    /*
        11、runAfterEither方法
        两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
     */

    private static void runAfterEither() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);

            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("f1 = " + t);
            return t;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);

            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("f2 = " + t);
            return t;
        });

        future1.runAfterEither(future2, () -> System.out.println("上面有一个已经完成了。"));
    }

    /*
        12、runAfterBoth
        两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
     */

    private static void runAfterBoth() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);

            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("f1 = " + t);
            return t;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);

            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("f2 = " + t);
            return t;
        });

        CompletableFuture<Void> result = future1.runAfterBoth(future2,
                () -> System.out.println("上面两个任务都执行完成了。"));
        result.get();
    }

    /*
        13、thenCompose方法
        thenCompose方法允许你对两个CompletionStage进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个参数。
     */

    private static void thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            System.out.println("t1 = " + t);
            return t;
        }).thenCompose(param -> CompletableFuture.supplyAsync(() -> {
            int t = param * 2;
            System.out.println("t2 = " + t);
            return t;
        }));

        System.out.println("thenCompose result: " + future.get());
    }
}
