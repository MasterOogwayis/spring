package com.zsw.persistence.repository.impl;

import com.zsw.base.repository.impl.CustomRepositoryImpl;
import com.zsw.persistence.bean.User;
import com.zsw.persistence.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZhangShaowei on 2017/9/12 13:45
 */
@Repository
public class UserRepositoryImpl extends CustomRepositoryImpl<User> implements UserRepository {


    /**
     * @param username username
     * @return List<User>
     */
    @Override
    public List<User> findByName(final String username) {
        String hql = "from User u where u.username = ?";
        return this.findByParam(hql, username);
    }
}
