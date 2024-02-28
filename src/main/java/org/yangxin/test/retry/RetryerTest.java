package org.yangxin.test.retry;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2022/5/11 16:07
 */
@SuppressWarnings("CallToPrintStackTrace")
public class RetryerTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfException()
//                .retryIfExceptionOfType(IOException.class)
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                .build();

        System.out.println(LocalDateTime.now());

        try {
            retryer.call(() -> {
                System.out.println("call");

                throw new NullPointerException();
//                throw new IOException();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(LocalDateTime.now());
    }
}
