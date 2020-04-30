package com.zsw.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/4/30 9:59
 */
@RestController
@RequestMapping("test")
public class ConfigurationController {

    @Autowired
    private PropertySourceService propertySourceService;


    @GetMapping
    public Object hello() {
        return this.propertySourceService.user();
    }

}
