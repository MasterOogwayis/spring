package com.boot.service.User;

import com.boot.persistence.domain.User;
import com.boot.persistence.repository.UserRepository;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
     * @return User
     */
    @Cacheable(value = "users", key = "'user:' + #id")
    public User get(final Long id) {
        User user = this.userRepository.findOne(id);
        this.log.debug(user);
        return user;
    }


}
