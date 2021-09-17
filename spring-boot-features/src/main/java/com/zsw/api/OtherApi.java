package com.zsw.api;

import com.zsw.aop.WorkService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2021/9/17 17:10
 */
@RequestMapping("other")
@RestController
public class OtherApi {

    @Autowired
    private ObjectProvider<WorkService> services;

    @GetMapping("test")
    public Object test() {
        services.stream().forEach(WorkService::doSomething);
        return "success";
    }

}
