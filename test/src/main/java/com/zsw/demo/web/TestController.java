package com.zsw.demo.web;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator on 2019/10/28 23:22
 **/
@RequestMapping("wait")
@RestController
public class TestController {

    @GetMapping
    @SneakyThrows
    public Object test() {
        TimeUnit.SECONDS.sleep(120);
        return "success";
    }


}
