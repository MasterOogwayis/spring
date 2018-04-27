package com.zsw.service.user;

import com.zsw.base.service.BaseService;
import com.zsw.persistence.timedtask.bean.User;

/**
 * @author ZhangShaowei on 2017/9/12 16:07
 */
public interface UserService extends BaseService<User, Long> {


    /**
     * @param username username
     * @return user
     */
//    @Cacheable(value = "list", key = "'user:' + #name", condition = "#name != null")
    User getByUsername(final String username);


}
