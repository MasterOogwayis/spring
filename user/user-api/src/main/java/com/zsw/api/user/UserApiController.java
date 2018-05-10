package com.zsw.api.user;

import com.zsw.persistence.user.bean.User;
import com.zsw.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2017/9/12 16:27
 */
@RestController
@RequestMapping("user")
public class UserApiController {

    /**
     *
     */
    @Autowired
    private UserService userService;


    /**
     * @param id id
     * @return user
     */
    @PostMapping("get")
    public User get(@RequestParam("id") final Long id) {
        User user = this.userService.get(id);
        return user;
    }


}
