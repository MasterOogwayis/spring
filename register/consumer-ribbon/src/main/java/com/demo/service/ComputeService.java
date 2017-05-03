package com.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author ZhangShaowei on 2017/4/28 13:18
 */
@Service
public class ComputeService {

    /**
     *
     */
    @Autowired
    private RestTemplate restTemplate;


    /**
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public String add(){
        MultiValueMap<String, Integer> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("a", 10);
        requestEntity.add("b", 20);
        return this.restTemplate.postForEntity("http://compute-service/add", requestEntity, String.class).getBody();
    }


    /**
     * @return
     */
    private String addFail(){
        return "error";
    }


}
