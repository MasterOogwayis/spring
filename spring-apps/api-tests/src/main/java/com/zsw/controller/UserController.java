package com.zsw.controller;

import com.zsw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/7/10 15:28
 */
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserRepository repository;


    @GetMapping
    public Object findAll() {
        PageRequest pageRequest = PageRequest.of(0, 1);
        return this.repository.findByName("zsw", pageRequest);
    }

}
