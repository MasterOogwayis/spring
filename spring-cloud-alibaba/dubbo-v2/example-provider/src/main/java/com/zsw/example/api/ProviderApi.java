package com.zsw.example.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/8/31 13:52
 */
@RequestMapping("provider")
@RestController
public class ProviderApi {

    @GetMapping("sum")
    public Object sum(@RequestParam("a") Integer a, @RequestParam("b") Integer b) {
        return a + b;
    }

}
