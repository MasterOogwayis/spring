package com.zsw.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/11/6 14:53
 **/
@RestController
public class EchoController {

    @RequestMapping("echo")
    public String echo(@RequestParam("message") String message) {
        return "Echo from server: " + message;
    }


}
