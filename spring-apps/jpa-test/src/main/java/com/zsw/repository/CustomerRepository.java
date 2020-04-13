package com.zsw.repository;

import com.zsw.orm.repository.BaseRepository;
import com.zsw.repository.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZhangShaowei on 2019/12/26 10:58
 **/
@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long>, CustomerRepositoruCustom {

    @Query("from Customer  where id > :id")
    List<Customer> findByParams(@Param("id") Long id);

}
