package com.boot.service.user;

import com.boot.persistence.domain.User;
import com.boot.persistence.repository.UserRepository;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhangShaowei on 2017/4/25 14:18
 */
@Service
public class UserService {

    /**
     *
     */
    private Logger log = Logger.getLogger(this.getClass());

    /**
     *
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * @param id id
     * @return user
     */
    @Cacheable(value = "users", key = "'user:' + #id")
    public User get(final Long id) {
        return this.userRepository.findOne(id);
    }

    /**
     * @return
     */
    @Cacheable(value = "list", key = "'user_list'")
    public List<User> findAll(){
        return this.userRepository.findAll();
    }


}
