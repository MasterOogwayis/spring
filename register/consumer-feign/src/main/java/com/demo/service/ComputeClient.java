package com.demo.service;
/**
 * @author ZhangShaowei on 2017/4/27 16:04
 */

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * feign service
 *
 * @author ZhangShaowei on 2017/4/27 16:04
 **/
@FeignClient(value = "service-provider", fallback = ComputeClientHystrix.class)
public interface ComputeClient {

    /**
     * @param a int
     * @param b int
     * @return sum
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    Integer add(@RequestParam("a") Integer a, @RequestParam("b") Integer b);

    /**
     * @param name string
     * @return string
     */
    @RequestMapping(value = "/hi", method = RequestMethod.POST)
    String hi(@RequestParam("name") String name);

}
