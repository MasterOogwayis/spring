package com.boot.persistence.repository;
/**
 * @author ZhangShaowei on 2017/4/24 14:44
 */

import com.boot.persistence.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * user Repository
 *
 * @author ZhangShaowei on 2017/4/24 14:44
 **/
@Repository
@Table(name = "USER")
@Qualifier("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * @param name name
     * @return
     */
    @Query("from user u where u.name = :name")
    User findByName(@Param("name") String name);

}
