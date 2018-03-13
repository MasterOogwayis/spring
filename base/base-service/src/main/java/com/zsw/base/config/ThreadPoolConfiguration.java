package com.zsw.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Spring的线程池
 * implements AsyncConfigurer
 *
 * @author ZhangShaowei on 2017/7/3 10:56
 */
@Configuration
@EnableAsync
public class ThreadPoolConfiguration {

    /**
     * 核心线程数
     */
    private static final int CORE_POOL_SIZE = 1;

    /**
     * 最大线程数:CPU核心数-N  1.计算密集型：N + 1   2.IO密集型：2N+1
     */
    private static final int MAX_POOL_SIZE = 9;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private static final int KEEP_ALIVE_SECONDS = 60;

    /**
     * 队列最大长度 >=mainExecutor.maxSize
     */
    private static final int QUEUE_CAPACITY = 300;

    /**
     * @return
     */
    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        executor.setThreadNamePrefix("ThreadPool-Executor-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }


    /**
     * @return
     */
//    @Bean
//    public AsyncTaskExecutor taskExecutor() {
//        return (ThreadPoolTaskExecutor) threadPoolTaskExecutor();
//    }

//    @Override
//    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//        return MyAsyncUncaughtExceptionHandler();
//    }

}
