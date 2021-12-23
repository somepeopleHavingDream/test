package org.yangxin.test.classinjdk.thread;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2021/8/26 17:26
 */
@SuppressWarnings({"divzero", "NumericOverflow", "AlibabaUndefineMagicConstant", "CommentedOutCode", "DuplicatedCode"})
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
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

        future.get();
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

        TimeUnit.SECONDS.sleep(2);
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
