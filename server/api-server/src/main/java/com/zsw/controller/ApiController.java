package com.zsw.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
public class ApiController {

    @GetMapping
    public Object rest(@RequestParam(value = "message", required = false) String message) {
        log.debug("message= {}", message);
        return message;
    }

    @GetMapping("/pause")
    @SneakyThrows
    public String pause() {
        Thread.sleep(20 * 1000);
        return "Pause complete";
    }

}
