/*
 * Copyright (C) 2020 bcpt Inc. All rights reserved.
 */
package com.zsw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author luolei
 */
@Configuration
@EnableAsync
public class ExecutorOcConfiguration {

    /** Set the ThreadPoolExecutor's core pool size. */
    private final int corePoolSize = 3;
    /** Set the ThreadPoolExecutor's maximum pool size. */
    private final int maxPoolSize = 4;
    /** Set the capacity for the ThreadPoolExecutor's BlockingQueue. */
    private final int queueCapacity = 10;

    @Bean
    public Executor ocTicketChangeAsync() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("ocTicketChange-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }


}
