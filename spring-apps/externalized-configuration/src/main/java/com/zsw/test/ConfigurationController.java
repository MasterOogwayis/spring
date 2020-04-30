package com.zsw.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/4/30 9:59
 */
@RestController
@RequestMapping("test")
public class ConfigurationController {

    @Autowired
    private PropertySourceService propertySourceService;

    @Autowired
    private Environment environment;


    @GetMapping("user")
    public Object user() {
        return this.propertySourceService.user();
    }

    @GetMapping("environment")
    public Object user(@RequestParam("name") String name) {
        return this.environment.getProperty(name);
    }

}
