package com.zsw.service.user.impl;

import com.zsw.base.service.impl.BaseServiceImpl;
import com.zsw.persistence.timedtask.bean.User;
import com.zsw.persistence.timedtask.repository.UserRepository;
import com.zsw.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangShaowei on 2017/9/12 16:07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    /**
     *
     */
    @Autowired
    private UserRepository userRepository;


    @Override
    public JpaRepository<User, Long> getRepository() {
        return this.userRepository;
    }

    /**
     * @param username username
     * @return user
     */
//    @Cacheable(value = "list", key = "'user:' + #name", condition = "#name != null")
    public User getByUsername(final String username) {
        return this.userRepository.getByUsername(username);
    }

}
