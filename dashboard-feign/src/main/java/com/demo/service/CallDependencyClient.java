package com.demo.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ZhangShaowei on 2017/6/1 15:27
 */
@FeignClient(value = "service-provider", fallback = CallDependencyClientImpl.class)
public interface CallDependencyClient {

    /**
     * @param a
     * @param b
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    Integer add(@RequestParam("a") Integer a, @RequestParam("b") Integer b);


}
