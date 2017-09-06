package com.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ZhangShaowei on 2017/8/30 10:24
 */
@RestController
public class ServiceHiController {

    /**
     *
     */
    private static final Logger log = Logger.getLogger(ServiceHiController.class.getName());


    /**
     *
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * @return
     */
    @GetMapping("/hi")
    public String callHome(){
        log.log(Level.INFO, "calling trace service-hi  ");
        return restTemplate.getForObject("http://localhost:8081/miya", String.class);
    }

    /**
     * @return
     */
    @GetMapping("/info")
    public String info(){
        log.log(Level.INFO, "calling trace service-hi ");

        return "i'm service-hi";
    }



}
