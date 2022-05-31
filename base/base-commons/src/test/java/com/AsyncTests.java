package com;

import com.zsw.function.ThrowableSupplier;

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

            execute(() -> TimeUnit.SECONDS.sleep(5));
            return "hello World";

        }, executor);

        execute(() -> CompletableFuture.allOf(completableFuture).get());

        String data = ThrowableSupplier.execute(completableFuture::get);

        System.out.println(data);

        executor.shutdownNow();


    }


}
