package com.zsw.service.user;

import com.zsw.base.service.BaseService;
import com.zsw.persistence.bean.User;
import com.zsw.persistence.repository.BaseUserRepository;
import com.zsw.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangShaowei on 2017/9/12 16:07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService extends BaseService {

    /**
     *
     */
//    @Autowired
//    private UserRepository userRepository;

    /**
     *
     */
    @Autowired
    private BaseUserRepository baseUserRepository;


    /**
     * @param id id
     * @return user
     */
//    @Cacheable(value = "users", key = "'user:' + #id", condition = "#id != null")
    public User get(final Long id) {
//        User user = new User();
//        user.setId(1L);
//        user.setAddress("Eeatch");
//        user.setAge(20);
//        user.setName("James");
//        return user;
        User user = this.baseUserRepository.findOne(id);
        return user;
    }

    /**
     * @param username username
     * @return User
     */
//    @Cacheable(value = "list", key = "'user:' + #name", condition = "#name != null")
    public User getByUsername(final String username) {
        return this.baseUserRepository.getByUsername(username);
    }


    /**
     * @return
     */
    public List<User> findAll() {
        return this.baseUserRepository.findAll();
    }

    /**
     * @param num
     * @param users
     * @return
     */
//    @CachePut(value = "list", key = "'users:' + #num", condition = "#users != null")
//    public List<User> test(String num, List<User> users) {
//        return users;
//    }

}
