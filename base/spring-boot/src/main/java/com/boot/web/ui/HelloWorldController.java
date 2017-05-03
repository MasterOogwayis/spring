package com.boot.web.ui;

import com.boot.persistence.domain.User;
import com.boot.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2017/4/24 10:58
 */
@RestController
public class HelloWorldController {

    /**
     *
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * @return String
     */
    @RequestMapping("hello")
    public String index() {
        return "Hello World!";
    }

    /**
     * @param name name
     * @return User
     */
    @RequestMapping("/user/get")
    public User get(@RequestParam final String name) {
        return this.userRepository.findByName(name);
    }

}
