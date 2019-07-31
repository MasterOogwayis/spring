package com;

import com.zsw.orm.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author ZhangShaowei on 2019/7/29 15:39
 **/
@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long> {

    @Query("from Customer where name = :name")
    Customer getByName(@Param("name") String name);

}
