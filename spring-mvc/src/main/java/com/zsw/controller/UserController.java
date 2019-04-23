package com.zsw.controller;

import com.zsw.persistence.entity.User;
import com.zsw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Shaowei Zhang on 2019/4/24 0:48
 **/
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("get")
    public User getByUsername(@RequestParam("username") String username) {
        return this.userService.getBuUsername(username);
    }

    @PostMapping("save")
    public String save(@RequestBody User user) {
        return this.userService.save(user);
    }



}
