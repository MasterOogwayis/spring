package com.zsw.persistence.repository;

import com.zsw.base.repository.CustomRepository;
import com.zsw.persistence.bean.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * UserRepository
 *
 * @author ZhangShaowei on 2017/9/12 13:39
 **/
//@Repository
//@Table(name = "USER")
//@Qualifier("userRepository")
@NoRepositoryBean
public interface UserRepository extends CustomRepository<User> {

    /**
     * @param name name
     * @return List<User>
     */
    @Query("from User u where u.name = :name")
    List<User> findByName(@Param("name") String name);

}
