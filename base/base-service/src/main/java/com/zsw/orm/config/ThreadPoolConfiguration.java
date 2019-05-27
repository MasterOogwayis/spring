package com.zsw.orm.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties(
        prefix = "com.zsw.application.thread-pool"
)
@Getter
@Setter
@Configuration
@EnableAsync
public class ThreadPoolConfiguration {

    /**
     * 核心线程数
     */
    private Integer corePoolSize = 4;

    /**
     * 最大线程数:CPU核心数-N  1.计算密集型：N + 1   2.IO密集型：2N+1
     */
    private Integer maxPoolSize = Runtime.getRuntime().availableProcessors() * 2 +1;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private Integer keepAliveSeconds = 60;

    /**
     * 队列最大长度 >=mainExecutor.maxSize
     */
    private Integer queueCapacity = 500;

    /**
     * ExecutorService ??
     *
     * @return
     */
    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
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
