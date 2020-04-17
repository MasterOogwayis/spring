package com.zsw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/4/17 13:28
 */
@RequestMapping("ping")
@RestController
public class TestController {

    @GetMapping
    public String ping() {
        return "pong";
    }


}
