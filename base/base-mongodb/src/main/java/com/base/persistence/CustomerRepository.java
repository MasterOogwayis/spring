package com.base.persistence;

import com.base.persistence.entity.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2021/10/21 10:45
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    /**
     * Derived query using dynamic sort information.
     *
     * @param lastname
     * @param sort
     * @return
     */
    List<Customer> findByLastName(String lastname, Sort sort);

//    /**
//     * Show case for a repository query using geo-spatial functionality.
//     *
//     * @param point
//     * @param distance
//     * @return
//     */
//    GeoResults<Customer> findByAddressLocationNear(Point point, Distance distance);

    @Query("{}")
    Stream<Customer> findAllByCustomQueryWithStream();

}
