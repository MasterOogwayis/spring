package com.zsw.persistence.repository;

import com.zsw.data.orm.repository.BaseDao;
import com.zsw.data.orm.repository.support.SimpleDao;
import com.zsw.persistence.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author Shaowei Zhang on 2019/4/24 0:27
 **/
@Repository
public class UserRepository extends SimpleDao<User, Long> implements BaseDao<User, Long> {
}
