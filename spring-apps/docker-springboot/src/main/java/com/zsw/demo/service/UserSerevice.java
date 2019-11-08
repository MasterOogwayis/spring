package com.zsw.demo.service;

import com.zsw.demo.persistence.entity.User;

/**
 * @author ZhangShaowei on 2019/11/8 13:23
 **/
public interface UserSerevice {

    User getById(Long id);

    void save(User user);

}
