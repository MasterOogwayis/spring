package com.demo;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2021/11/17 9:23
 */
@RestController
public class NacosApi implements EnvironmentAware {

    private Environment environment;

    @GetMapping("get")
    public String get(@RequestParam("key") String key) {
        return this.environment.getProperty(key);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
