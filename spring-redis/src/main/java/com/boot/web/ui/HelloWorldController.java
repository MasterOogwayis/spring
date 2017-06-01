package com.boot.web.ui;

import com.boot.persistence.domain.User;
import com.boot.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ZhangShaowei on 2017/4/24 10:58
 */
@RestController
public class HelloWorldController {

    /**
     *
     */
    @Autowired
    private UserService userService;

    /**
     *
     */
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * @param id id
     * @return User
     */
    @PostMapping("/{id}")

    public User getById(@PathVariable final Long id) {
        return this.userService.get(id);
    }

    /**
     * @return
     */
    @PostMapping("/findAll")
    public List<User> getById() {
        return this.userService.findAll();
    }

    /**
     * @return
     */
    @PostMapping("/set")
    public String set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return "true";
    }

}
