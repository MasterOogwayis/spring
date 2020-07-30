package com.zsw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/7/30 11:34
 */
@RestController
@RequestMapping("redis")
public class SendController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("send")
    public Object send(@RequestParam("msg") String message) {
        this.redisTemplate.convertAndSend("test:channel", message);
        return "success";
    }

}
