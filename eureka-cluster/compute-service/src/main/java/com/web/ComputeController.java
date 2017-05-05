package com.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2017/5/3 10:15
 */
@RestController
public class ComputeController {

    /**
     *
     */
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     *
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * @param a a
     * @param b b
     * @return sum
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Integer add(@RequestParam final Integer a, @RequestParam final Integer b) {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        Integer num = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + num);
        return num;
    }

}
