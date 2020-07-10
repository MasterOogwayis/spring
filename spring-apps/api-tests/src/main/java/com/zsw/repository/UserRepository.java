package com.zsw.repository;

import com.zsw.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZhangShaowei on 2020/7/10 15:26
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * @param name
     * @param page
     * @return
     */
    @Query("from User where name = :name")
    List<User> findByName(@Param("name") String name, Pageable page);

}
