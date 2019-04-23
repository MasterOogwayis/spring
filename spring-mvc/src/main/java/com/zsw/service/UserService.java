package com.zsw.service;

import com.zsw.persistence.entity.User;
import com.zsw.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Shaowei Zhang on 2019/4/24 0:29
 **/
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User getBuUsername(String username) {
        List<User> list =  this.userRepository.find(User.builder().username(username).build());
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    public String save(User user) {
        this.userRepository.save(user);
        return "success";
    }


}
