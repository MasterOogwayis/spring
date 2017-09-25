package com.zsw.service;

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
    @HystrixCommand(fallbackMethod = "fail")
    public String add(){
        MultiValueMap<String, Integer> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("a", 10);
        requestEntity.add("b", 20);
        return this.restTemplate.postForEntity("http://service-provider/add", requestEntity, String.class).getBody();
    }


    /**
     * @return
     */
    @HystrixCommand(fallbackMethod = "fail")
    public String hi(){
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("name", "ribbon");
        return this.restTemplate.postForEntity("http://service-provider/hi", requestEntity, String.class).getBody();
    }

    /**
     * @return
     */
    private String fail(){
        return "fail";
    }


}
