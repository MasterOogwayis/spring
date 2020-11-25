package com.zsw.orm.config;


import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.task.TaskExecutorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * FIXME 事实上我们常用的线程池一般时候只会扩容到核心数量，需要队列满了入队失败才会继续增加 worker 到设置的上限
 * 如何让它在我们期望中随着任务增加进行扩容呢，看 dubbo 的方案
 * org.apache.dubbo.common.threadpool.support.eager.EagerThreadPoolExecutor
 *
 * @author ZhangShaowei on 2019-8-2 15:35:56
 * @author ZhangShaowei on 2017/7/3 10:56
 * @see TaskExecutionAutoConfiguration
 * @see TaskSchedulingAutoConfiguration
 * @deprecated Spring Boot 2.x 以后提供自动装配 线程池 / 调度, 无需求自行配置
 * <p>
 * AbortPolicy         -- 当任务添加到线程池中被拒绝时，对拒绝任务抛弃处理，并且抛出异常。它将抛出 RejectedExecutionException 异常。
 * CallerRunsPolicy    -- 当任务添加到线程池中被拒绝时，会在线程池当前正在运行的Thread线程池中处理被拒绝的任务。(使用调用线程执行)
 * DiscardOldestPolicy -- 当任务添加到线程池中被拒绝时，线程池会抛弃队列里面等待最久的一个线程，然后将被拒绝的任务添加到等待队列中。
 * DiscardPolicy       -- 当任务添加到线程池中被拒绝时，对拒绝任务直接无声抛弃，没有异常信息。
 */
@Configuration
@EnableAsync
@EnableScheduling
public class ThreadPoolConfiguration {

    /**
     * 使用以下修改默认配置，或通过配置方式修改
     *
     * @return
     */
    @Bean
    public TaskExecutorCustomizer taskExecutorCustomizer() {
        return new TaskExecutorCustomizer() {
            @Override
            public void customize(ThreadPoolTaskExecutor taskExecutor) {
                taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            }
        };
    }

}
