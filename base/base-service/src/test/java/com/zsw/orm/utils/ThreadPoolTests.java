package com.zsw.orm.utils;

import lombok.SneakyThrows;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2021/8/9 11:01
 */
public class ThreadPoolTests {


    @SneakyThrows
    public static void main(String[] args) {
        ThreadPoolTaskExecutor executor = ThreadPoolUtils.create();
        executor.initialize();

        Future<Object> submit = executor.submit(() -> {
            throw new NullPointerException("2");
        });

        executor.execute(() -> {
            throw new NullPointerException("1");
        });


        TimeUnit.SECONDS.sleep(5);
        executor.shutdown();

    }


    @SneakyThrows
    private static void testPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                4,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());


        for (int i = 0; i < 10; i++) {
            int j = i;
            executor.execute(() -> {
                System.out.println(j);
                try {
                    TimeUnit.DAYS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        TimeUnit.DAYS.sleep(2);
        executor.shutdown();
    }

}
