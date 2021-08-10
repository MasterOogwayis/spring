package com.zsw.orm.utils;

import lombok.SneakyThrows;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2021/8/9 11:01
 */
public class ThreadPoolTests {

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
            2,
            4,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(2),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    @SneakyThrows
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            int j = i;
            EXECUTOR.execute(() -> {
                System.out.println(j);
                try {
                    TimeUnit.DAYS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        TimeUnit.DAYS.sleep(2);
        EXECUTOR.shutdown();
    }

}
