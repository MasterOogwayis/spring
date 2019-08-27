package com;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2019/4/26 10:46
 **/
@Slf4j
@RestController
@RequestMapping("test")
public class SimpleController {

    @Autowired
    private ThreadPoolTaskExecutor executor;


    @SneakyThrows
    @GetMapping("executor")
    public Object test() {
        Future<String> submit = this.executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                return "success";
            }
        });
        log.info("wait");
        return submit.get();
    }

}
