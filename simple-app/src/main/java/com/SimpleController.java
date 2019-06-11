package com;

import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangShaowei on 2019/4/26 10:46
 **/
@RequestMapping("test")
@RestController
public class SimpleController {


    @GetMapping("Hello")
    public Object Hello(@RequestParam("name") String name) {
        return "Hello " + name;
    }


    @PostMapping("post")
    public Object post(@RequestBody String requestBody) {
        System.out.println(requestBody);
        return requestBody;
    }


}
