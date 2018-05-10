package com.zsw.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * NodeController
 *
 * @author ZhangShaowei on 2018/5/10 15:34
 **/
@RestController
@RequestMapping("resource")
public class NodeController {

    /**
     * @return
     */
    @GetMapping("get")
    public Object get(String key) {
        return Collections.singletonMap("get", key);

    }

    @PostMapping("post")
    public Object post(String key) {
        return Collections.singletonMap("post", key);

    }


}
