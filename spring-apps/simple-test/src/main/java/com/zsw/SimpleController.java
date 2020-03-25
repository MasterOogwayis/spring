package com.zsw;

import com.zsw.metrics.service.HealthService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private HealthService healthService;

    @SneakyThrows
    @GetMapping
    public Object get(@RequestParam Integer number) {
        this.healthService.setNum(number);
        return "success";
    }

}
