package com.zsw.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ResourceEndPoint
 *
 * @author ZhangShaowei on 2018/5/17 15:26
 **/
@RestController
@RequestMapping("resource")
public class ResourceEndPoint {

    @PostMapping("get")
    public Object get(String id) {
        return "get: " + id;
    }

    @PostMapping("save")
    public Object save(String id) {
        return "save: " + id;
    }


}
