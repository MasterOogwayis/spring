package com.zsw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Repository;

/**
 * @author ZhangShaowei on 2020/9/25 14:05
 */
@Eager
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
