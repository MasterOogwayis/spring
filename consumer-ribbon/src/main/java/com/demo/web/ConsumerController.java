package com.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ZhangShaowei on 2017/4/26 14:54
 */
@RestController
public class ConsumerController {

    /**
     *
     */
    @Autowired
    private RestTemplate restTemplate;


    /**
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add() {
        MultiValueMap<String, Integer> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("a", 10);
        requestEntity.add("b", 20);
        return this.restTemplate.postForEntity("http://compute-service/add", requestEntity, String.class).getBody();
    }





}
