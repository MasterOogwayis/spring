package com.zsw.api;

import com.zsw.service.TestThisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2021/9/17 17:10
 */
@Slf4j
@RequestMapping("other")
@RestController
public class OtherApi {

    @Autowired
    private TestThisService service;

    @GetMapping("test")
    public Object test() {
        service.test1("This is a test.");
        return "success";
    }

    @GetMapping("log")
    public Object log(@RequestParam("p") String param) {
        log.info("param = {}", param);
        log.debug("param = {}", param);
        log.error("param = {}", param);
        log.warn("param = {}", param);
        return "success";
    }

}
