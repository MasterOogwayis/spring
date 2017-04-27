package com.demo.web;

import com.demo.client.ComputeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2017/4/27 16:08
 */
@RestController
public class ConsumerController {

    /**
     *
     */
    @Autowired
    private ComputeClient computeClient;


    /**
     * @return sum
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add() {
        return computeClient.add(10, 20);
    }

}
