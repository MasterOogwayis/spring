package com.test.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ZhangShaowei on 2019/6/21 11:24
 **/
@FeignClient(name = "test", url = "http://127.0.0.1")
public interface DoSomeThingService {

    @PostMapping("get")
    String get(@RequestParam("data") String data);

}
