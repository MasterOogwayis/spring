package com.zsw.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/4/18 9:15
 **/
@Slf4j
@RequestMapping
@RestController
//@ManagedResource(description = "emmm ...")
public class ApiController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping
    public Object rest(@RequestParam(value = "message", required = false) String message) {
        log.debug("message= {}", message);
        return message;
    }

    @GetMapping("/pause")
    @SneakyThrows
    public String pause(@RequestParam("seconds") Integer seconds) {
        for (int i = 0; i < seconds; i++) {
            log.warn(" Shutdown count down {}", seconds - i);
            Thread.sleep(1000);
        }
        return "Pause complete";
    }


//    @ManagedOperation(description = "shutdown")
//    public void shutdown() {
//        SpringApplication.exit(this.applicationContext);
//    }

}

