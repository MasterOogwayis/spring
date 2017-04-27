package com.demo.client;
/**
 * @author ZhangShaowei on 2017/4/27 16:04
 */

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * feign client
 *
 * @author ZhangShaowei on 2017/4/27 16:04
 **/
@FeignClient("compute-service")
public interface ComputeClient {

    /**
     * @param a int
     * @param b int
     * @return sum
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    Integer add(@RequestParam("a") Integer a, @RequestParam("b") Integer b);

}
