package com.zsw.test.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author ZhangShaowei on 2020/5/11 14:27
 */
@FeignClient(name = "simple-api-test", url = "http://127.0.0.1:8081", fallback = UserClientFallback.class)
public interface UserClient {

    @GetMapping("/simple/getUserInfo")
    Map<String, String> getUserInfo(@RequestParam("username") String username);

}
