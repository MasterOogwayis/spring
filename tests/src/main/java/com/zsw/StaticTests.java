package com.zsw;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author ZhangShaowei on 2020/12/28 16:03
 */
public class StaticTests {

    public static void main(String[] args) {
        Executor executor = mock(Executor.class);
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> 0);
        completableFuture.thenRunAsync(mock(Runnable.class), executor)
                .whenComplete((s, e) -> {
                    verify(executor, times(1)).execute(any(Runnable.class));
                });


    }

}
