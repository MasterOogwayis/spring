package com.zsw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZhangShaowei on 2020/9/25 14:05
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
