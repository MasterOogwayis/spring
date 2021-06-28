package com.zsw.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2020/9/21 13:13
 */
@Service
public class ApiService {

    @Autowired
    private ThreadPoolTaskExecutor executor;

    private volatile Thread task;

    @Autowired
    private ApiService _self;

    public String hello(String name) {
        return "Hello " + name;
    }


    public void trigger() {
        if (this.executor.getActiveCount() != 0) {
            this.task.interrupt();
        } else {
            this.task = this.executor.newThread(() -> {
                while (true) {
                    try {
                        ApiService.this._self.hello("name");
                        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(20));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            this.executor.execute(task);
        }
    }
}
