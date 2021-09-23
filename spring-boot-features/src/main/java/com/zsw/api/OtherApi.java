package com.zsw.api;

import com.zsw.service.TestThisService;
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
    private TestThisService service;

    @GetMapping("test")
    public Object test() {
        service.test1("This is a test.");
        return "success";
    }

}
