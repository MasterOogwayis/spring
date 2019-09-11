package com.zsw.web;

import com.netflix.discovery.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/4/12 9:45
 **/
@Slf4j
@RequestMapping("apps")
@RestController
public class ApiController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping
    public Object api(@RequestParam("message") String message) {
        this.discoveryClient.getApplications();
        return message;
    }


}
