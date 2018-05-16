package com.api;

import com.zsw.persistence.user.bean.User;
import com.zsw.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author ZhangShaowei on 2018/5/15 11:27
 **/
@RestController
@RequestMapping("test")
public class TestController {

    /**
     *
     */
    @Autowired
    private SecurityService securityService;


    @GetMapping("get")
    public User get(Long id) {
        return this.securityService.get(id);
    }

}
