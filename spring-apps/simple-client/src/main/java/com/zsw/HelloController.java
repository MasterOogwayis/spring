package com.zsw;

import com.zsw.client.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author ZhangShaowei on 2020/5/11 14:29
 */
@RequestMapping
@RestController
public class HelloController {

    @Autowired
    private HelloClient helloClient;

    @Autowired
    private RestTemplate restTemplate;



    @GetMapping("hello")
    public String hello(@RequestParam("name") String name) {
        return this.helloClient.hello(name);
    }

    @GetMapping("restHello")
    public String restHello(@RequestParam("name") String name) {
        ResponseEntity<String> responseEntity = this.restTemplate
                .getForEntity("http://simple-api/test/hello", String.class, Collections.singletonMap("name", name));
        return responseEntity.getBody();
    }

}
