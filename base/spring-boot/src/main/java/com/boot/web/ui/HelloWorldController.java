package com.boot.web.ui;

import com.boot.persistence.domain.User;
import com.boot.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private UserRepository userRepository;

    /**
     * @return String
     */
    @PostMapping("hello")
    public String index() {
        return "Hello World!";
    }

    /**
     * @param name name
     * @return user
     */
    @PostMapping("/user/get")
    public User get(@RequestParam final String name) {
        User user = this.userRepository.findByName(name);
        return user;
    }

    /**
     * @param id id
     * @return user
     */
    @PostMapping("/{id}")
    public User getById(@PathVariable final Long id) {
//        user user = this.userRepository.findOne(id);
        return null;
    }

    /**
     * @return
     */
    @PostMapping("findAll")
    public List<User> findAll() {
        List<User> users = this.userRepository.findAll();
        return users;
    }

}
