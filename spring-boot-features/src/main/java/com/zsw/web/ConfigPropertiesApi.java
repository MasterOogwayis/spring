package com.zsw.web;

import com.zsw.property.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhangShaowei on 2021/8/23 10:19
 */
@RestController
public class ConfigPropertiesApi {

    @Autowired
    private MyProperties mapProperties;

    @Value("${custom.name:nobody}")
    private String name;

    @GetMapping("config")
    public Object map(HttpServletRequest request) {
        return this.mapProperties;
    }

    @GetMapping("name")
    public String name() {
        return this.name;
    }

}
