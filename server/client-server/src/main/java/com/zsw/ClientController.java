package com.zsw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/9/18 11:49
 **/
@RequestMapping("api")
@RestController
public class ClientController {

    @Autowired
    private ApiClient apiClient;

    @GetMapping("hello")
    public String hello(@RequestParam("name") String name) {
        return this.apiClient.hello(name);
    }

}
