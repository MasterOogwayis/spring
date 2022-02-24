package com.tests;

import com.zsw.util.concurrent.EagerThreadPoolExecutor;
import com.zsw.util.concurrent.TaskQueue;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2022/2/24 9:49
 */
@Slf4j
public class EagerThreadPoolTests {

    public static void main(String[] args) {
        TaskQueue<Runnable> taskQueue = new TaskQueue<>(10);

        EagerThreadPoolExecutor executor = new EagerThreadPoolExecutor(
                1, 4,
                60, TimeUnit.SECONDS,
                taskQueue,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        taskQueue.setExecutor(executor);

        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("End sleep ...{}", Thread.currentThread().getName());
        });

        executor.execute(() -> {
            log.info("Second ...{}", Thread.currentThread().getName());
        });




    }

}
