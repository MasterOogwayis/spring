package com.zsw.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author ZhangShaowei on 2021/2/9 10:16
 */
@FeignClient(name = "to-smc", url = "http://39.107.37.83:7201", fallback = MessageClientHystrix.class)
public interface MessageClient {

    @PostMapping("/message/app/customer/send")
    String send(@RequestBody Map<String, Object> map);

    @PostMapping("/message/app/customer/send1")
    String send1(@RequestBody Map<String, Object> map);

}
