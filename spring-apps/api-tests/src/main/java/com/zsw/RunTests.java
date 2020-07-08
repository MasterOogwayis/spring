package com.zsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.task.TaskExecutorCustomizer;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ZhangShaowei on 2020/7/8 9:09
 */
//@EnableFeignClients
//@EnableDiscoveryClient
@SpringBootApplication
public class RunTests {


    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(RunTests.class, args);
    }


    @Bean
    public TaskExecutorCustomizer taskExecutorCustomizer() {
        return taskExecutor -> taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
