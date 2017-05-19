package com.boot.service.User;

import com.boot.persistence.domain.User;
import com.boot.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2017/5/19 13:26
 */
@Service
public class UserRedisService {


    /**
     *
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * @param id id
     * @return User
     */
    @Cacheable(key = "'user:' + #id")
    public User get(final Long id){
        return this.userRepository.getOne(id);
    }




}
