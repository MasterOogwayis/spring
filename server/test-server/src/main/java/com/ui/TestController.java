package com.ui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2018/8/20 14:07
 **/
@RestController
@RefreshScope
public class TestController {

    @Value("${name}")
    private String name;

    @GetMapping("name")
    public String name() {
        return name;
    }


}
