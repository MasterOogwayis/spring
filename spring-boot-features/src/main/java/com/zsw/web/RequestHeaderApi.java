package com.zsw.web;

import com.zsw.resolver.RequestHeaderObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ZhangShaowei on 2021/8/23 10:19
 */
@RestController
public class RequestHeaderApi {

    @GetMapping("mapHeaders")
    public Object mapHeaders(@RequestHeaderObject Map<String, String> headers) {
        return headers;
    }

    @GetMapping("headers")
    public Object headers(@RequestHeaderObject HeaderRequest headers) {
        return headers;
    }

}
