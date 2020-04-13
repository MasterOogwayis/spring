package com.zsw.demo.web;

import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author ZhangShaowei on 2019/11/13 14:55
 **/
@RequestMapping("params")
@RestController
public class ParamController {

    @RequestMapping("{path}")
    public Object test(Dto dto, @PathVariable("path") String path) {
        return Collections.singletonMap(path, dto);
    }


    @Data
    private class Dto {
        private String name;
        private String address;
    }

}
