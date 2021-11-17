package com.zsw.persistence.repository;

import com.zsw.persistence.entity.TestUser;
import com.zsw.persistence.repository.custom.TestUserRepositoryCustom;
import com.zsw.pojo.NamesOnly;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZhangShaowei on 2021/8/26 10:33
 */
@Repository
public interface TestUserRepository extends JpaRepository<TestUser, Long>, TestUserRepositoryCustom {

    @Query("from TestUser")
    List<TestUser> findAllByPage(Pageable pageable);

    List<TestUser> findByPhone(String phone);

    @Query("from TestUser where name = :name")
    List<TestUser> findByName(@Param("name") String name);

    @Query("select c.id, c.name, c.address, c.age, c.phone from TestUser c where c.name like ?1%")
    List<Object[]> findByAndSort(String name, Pageable pageable);

    List<NamesOnly> findByFirstName(String firstName);

}
