package com.zsw.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author ZhangShaowei on 2021/2/9 10:16
 */
@FeignClient(name = "bcpt-smc-api", fallback = MessageClientHystrix.class)
public interface MessageClient {

    @GetMapping("/actuator/prometheus1")
    default String prometheus() {
        return "fallback prometheus1";
    }
    @GetMapping("/actuator/prometheus1")
    String prometheus1();



}
