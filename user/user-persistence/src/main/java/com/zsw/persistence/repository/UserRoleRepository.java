package com.zsw.persistence.repository;
/**
 * @author ZhangShaowei on 2017/9/21 14:08
 */

import com.zsw.base.repository.BaseRepository;
import com.zsw.persistence.bean.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserRoleRepository
 *
 * @author ZhangShaowei on 2017/9/21 14:08
 **/
@Repository
public interface UserRoleRepository extends BaseRepository<UserRole, Long> {

    /**
     * @param userId userId
     * @return List<UserRole>
     */
    @Query("from UserRole ur where ur.user.id = :id")
    List<UserRole> findByUserId(@Param("id") Long userId);

}
