package com.zsw;

import com.zsw.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Shaowei Zhang on 2022/2/24 21:26
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public ExecutorService executorService() {
        return Executors.eagerThreadPool(
                4, 16,
                60, TimeUnit.SECONDS,
                100,
                java.util.concurrent.Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }


}
