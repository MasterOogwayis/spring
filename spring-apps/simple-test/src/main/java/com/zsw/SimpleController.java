package com.zsw;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/11/28 16:46
 **/
@RestController
@RequestMapping("test")
public class SimpleController {


    @GetMapping("get")
    public Object test(Dto dto, @RequestParam("address") String add) {
        return dto;
    }

    @Data
    class Dto {
        private String name;
        private Integer age;
    }

}
