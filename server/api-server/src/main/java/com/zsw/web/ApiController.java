package com.zsw.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/4/12 9:45
 **/
@Slf4j
@RequestMapping("api")
@RestController
public class ApiController {

    @GetMapping
    public Object api(@RequestParam("message") String message) {
        log.info(message);
        return message;
    }


}
