package com.zsw.web;

import com.zsw.property.MyProperties;
import com.zsw.resolver.RequestHeaderObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ZhangShaowei on 2021/8/18 11:29
 */
@RestController
public class ApiController {

    @Autowired
    private MyProperties mapProperties;

    @Value("${custom.name:nobody}")
    private String name;

    @GetMapping("hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("name")
    public String name() {
        return this.name;
    }


    @GetMapping("mapHeaders")
    public Object mapHeaders(@RequestHeaderObject Map<String, String> headers) {
        return headers;
    }

    @GetMapping("headers")
    public Object headers(@RequestHeaderObject HeaderRequest headers) {
        return headers;
    }

    @GetMapping("config")
    public Object map() {
        return this.mapProperties;
    }

    @RequestMapping("body")
    private Object body(@RequestBody String json) {
        return json;
    }

}
