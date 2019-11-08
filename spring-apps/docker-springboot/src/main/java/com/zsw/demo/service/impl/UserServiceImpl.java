package com.zsw.demo.service.impl;

import com.zsw.demo.persistence.dao.UserMapper;
import com.zsw.demo.persistence.entity.User;
import com.zsw.demo.service.UserSerevice;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2019/11/8 13:23
 **/
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserSerevice {


    private UserMapper userMapper;

    @Override
    public User getById(Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(User user) {
        this.userMapper.insertSelective(user);
    }
}
