package com.zsw.demo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zsw.demo.annotation.Limited;
import com.zsw.demo.annotation.Timeout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator on 2019/9/22 16:53
 **/
@Slf4j
@RequestMapping
@RestController
public class DemoController {


    @Limited(1)
    @Timeout(value = 50L, fallback = "fallback")
    @GetMapping("hello")
    public Object hello(@RequestParam("name") String name) {
        this.await();
        return "Hello " + name;
    }

    @GetMapping("echo")
    @HystrixCommand(
            fallbackMethod = "fallback",
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "50")
            }
    )
    public Object echo(@RequestParam("name") String name) {
        log.info("thread={}, name={}", Thread.currentThread().getName(), name);
        this.await();
        return "Hello " + name;
    }





    private void await() {
        int i = ThreadLocalRandom.current().nextInt(100);
        log.info("thread={}, 等待时间：{}ms", Thread.currentThread().getName(), i);
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public Object fallback(String name) {
        log.warn("fallback, name={}", name);
        return "FALLBACK: " + name;
    }


}
