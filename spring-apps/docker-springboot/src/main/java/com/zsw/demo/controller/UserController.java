package com.zsw.demo.controller;

import com.zsw.demo.persistence.entity.User;
import com.zsw.demo.service.UserSerevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangShaowei on 2019/11/8 13:23
 **/
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserSerevice userSerevice;

    @GetMapping("{id}")
    public User get(@PathVariable("id") Long id) {
        return this.userSerevice.getById(id);
    }

    @PostMapping("save")
    public Object save(@RequestBody User user) {
        this.userSerevice.save(user);
        return "success";
    }

}
