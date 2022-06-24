package com;

import com.zsw.function.ThrowableSupplier;
import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.zsw.function.ThrowableExecutor.execute;

/**
 * @author ZhangShaowei on 2022/5/31 11:51
 */
public class AsyncTests {

    @SneakyThrows
    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors() * 2 + 1,
                200,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            execute(() -> TimeUnit.SECONDS.sleep(2));
            System.out.println("thread name: " + Thread.currentThread().getName());
            return "hello World";
        }, executor);

        // get() 会阻塞到所有任务执行完，只执行 allOf 只会触发任务，不会等待
//        execute(() -> CompletableFuture.allOf(completableFuture).get());
        CompletableFuture.allOf(completableFuture);

        TimeUnit.SECONDS.sleep(3);

        String data = ThrowableSupplier.execute(completableFuture::get);

        System.out.println(data);

        executor.shutdownNow();
    }


}
