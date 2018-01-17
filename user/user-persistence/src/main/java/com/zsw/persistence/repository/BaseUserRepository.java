package com.zsw.persistence.repository;

import com.zsw.base.repository.BaseRepository;
import com.zsw.base.repository.CustomRepository;
import com.zsw.persistence.bean.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * UserRepository
 *
 * @author ZhangShaowei on 2017/9/12 13:39
 **/
@Repository
@Table(name = "USER")
@Qualifier("baseUserRepository")
public interface BaseUserRepository extends BaseRepository<User, Long> {

    /**
     * @param username username
     * @return List<user>
     */
    @Query("from user u where u.username = :username")
    User getByUsername(@Param("username") String username);

}
