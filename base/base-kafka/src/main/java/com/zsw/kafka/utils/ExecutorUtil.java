package com.zsw.kafka.utils;

import java.util.concurrent.*;

/**
 * @author ZhangShaowei on 2019/8/20 13:41
 **/
public class ExecutorUtil {

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() * 2 + 1,
            200,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());


    public static void execute(Runnable runnable) {
        EXECUTOR.execute(runnable);
    }

    public static  <T> Future<T> submit(Callable<T> runnable) {
        return EXECUTOR.submit(runnable);
    }

}
