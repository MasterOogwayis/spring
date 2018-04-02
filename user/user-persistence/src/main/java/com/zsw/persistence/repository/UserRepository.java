package com.zsw.persistence.repository;

import com.zsw.persistence.bean.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserRepository
 *
 * @author ZhangShaowei on 2017/9/12 13:39
 **/
@Repository
//@Table(name = "USER")
@Qualifier("userRepository")
//@NoRepositoryBean
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * @param name name
     * @return List<user>
     */
    @Query("from user u where u.name = :name")
    List<User> findByName(@Param("name") String name);

    /**
     * @param name
     * @return
     */
    @Modifying
    @Query("delete from user u where u.name = :name")
    int delByName(@Param(("name")) String name);

}
