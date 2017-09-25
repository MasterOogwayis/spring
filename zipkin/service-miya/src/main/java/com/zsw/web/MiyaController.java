package com.zsw.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ZhangShaowei on 2017/8/30 10:36
 */
@RestController
public class MiyaController {

    private static final Logger log = Logger.getLogger(MiyaController.class.getName());

    /**
     *
     */
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hi")
    public String home(){
        log.log(Level.INFO, "hi is being called");
        return "hi i'm miya!";
    }

    @GetMapping("/miya")
    public String info(){
        log.log(Level.INFO, "info is being called");
        return restTemplate.getForObject("http://localhost:8080/info", String.class);
    }

}
