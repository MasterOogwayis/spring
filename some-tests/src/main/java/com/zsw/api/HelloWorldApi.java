package com.zsw.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2022/2/17 10:30
 */
@AllArgsConstructor
@RestController
public class HelloWorldApi {

    private final TestAdvisorService testAdvisorService;

    @GetMapping("hello")
    public String hello() {
        testAdvisorService.ahello();
        return testAdvisorService.hello();
    }


}
