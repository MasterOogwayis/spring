package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/4/26 10:46
 **/
@RequestMapping("test")
@RestController
public class SimpleController {

    @GetMapping
    public Object test() {
        return "success";
    }

}
