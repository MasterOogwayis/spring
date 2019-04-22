package com.zsw.persistence.repository;

import com.zsw.orm.repository.ZswRepository;
import com.zsw.orm.repository.impl.ZswRepositoryImpl;
import com.zsw.persistence.entity.Customer;
import org.springframework.stereotype.Repository;

/**
 * @author Shaowei Zhang on 2019/4/21 0:58
 **/
@Repository
public class CustomerRerpository extends ZswRepositoryImpl<Customer, Long> implements ZswRepository<Customer, Long> {
}
