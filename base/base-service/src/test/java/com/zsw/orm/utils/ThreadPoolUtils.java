package com.zsw.orm.utils;

import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.Duration;

/**
 * @author ZhangShaowei on 2021/8/11 10:37
 */
public class ThreadPoolUtils {

    public static ThreadPoolTaskExecutor create() {
        TaskExecutorBuilder builder = new TaskExecutorBuilder();
        builder = builder.queueCapacity(100);
        builder = builder.corePoolSize(2);
        builder = builder.maxPoolSize(8);
        builder = builder.allowCoreThreadTimeOut(false);
        builder = builder.keepAlive(Duration.ofSeconds(60));
        return builder.build(ThreadPoolTaskExecutor.class);
    }

}
