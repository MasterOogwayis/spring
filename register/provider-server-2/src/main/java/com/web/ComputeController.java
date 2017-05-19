package com.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;


/**
 * @author ZhangShaowei on 2017/4/26 10:07
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
     * @param a
     * @param b
     * @return sum
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Integer add(@RequestParam final Integer a, @RequestParam final Integer b) {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        Integer num = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + num);
        return num;
    }

    /**
     *
     */
    @Value("${server.port}")
    private String port;

    /**
     * @param name string
     * @return string
     */
    @PostMapping("/hi")
    public String hi(@RequestParam final String name) {
        return "hi " + name + ",i am from port:" + port;
    }


}
