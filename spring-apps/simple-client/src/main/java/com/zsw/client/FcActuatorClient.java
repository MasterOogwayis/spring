package com.zsw.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ZhangShaowei on 2020/7/7 10:55
 */
@FeignClient(value = "bcpt-fc-api", path = "actuator")
public interface FcActuatorClient {

    @GetMapping("health")
    String health();

    @GetMapping("prometheus")
    String prometheus();

}
