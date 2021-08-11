package com.zsw.example.api;

import com.zsw.service.dubbo.MathService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/8/31 11:20
 */
@RequestMapping("sum")
@RestController
public class MathApi {

    public MathApi() {
        System.out.println(123);
    }

    @DubboReference
    private MathService mathService;

    @GetMapping
    public Object sum(@RequestParam("a") Integer a, @RequestParam("b") Integer b) {
        return this.mathService.sum(a, b);
    }

}
