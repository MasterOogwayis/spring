package com.zsw.example.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/8/31 11:20
 */
@AllArgsConstructor
@RequestMapping("config")
@RestController
public class ConfigApi {

    private final ConfigService configService;

    @GetMapping
    public String get() {
        return this.configService.getName();
    }

}
