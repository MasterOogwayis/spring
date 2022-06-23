package com.zsw.util.concurrent;

import lombok.NoArgsConstructor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author Shaowei Zhang on 2022/2/24 21:24
 */
@NoArgsConstructor
public class Executors {

    public static ExecutorService eagerThreadPool(
            int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            int capacity,
            ThreadFactory threadFactory,
            RejectedExecutionHandler handler
    ) {
        TaskQueue<Runnable> taskQueue = new TaskQueue<>(capacity);

        var executor = new EagerThreadPoolExecutor(
                corePoolSize, maximumPoolSize,
                keepAliveTime, unit,
                taskQueue,
                threadFactory,
                handler
        );
        taskQueue.setExecutor(executor);
        return executor;
    }


}
