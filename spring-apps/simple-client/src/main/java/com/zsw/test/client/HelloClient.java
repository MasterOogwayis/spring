package com.zsw.test.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ZhangShaowei on 2020/5/11 14:27
 */
@FeignClient(value = "${services.api}", fallback = HelloClientFallback.class, configuration = HeaderConfiguration.class)
public interface HelloClient {

    @GetMapping("/test/hello")
    String hello(@RequestParam("name") String name);

}
