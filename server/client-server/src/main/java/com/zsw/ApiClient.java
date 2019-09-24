package com.zsw;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ZhangShaowei on 2019/9/18 11:50
 **/
@FeignClient("${com.zsw.instance.name.api}")
public interface ApiClient {

    @GetMapping("/api/hello")
    String hello(@RequestParam("name") String name);

}
