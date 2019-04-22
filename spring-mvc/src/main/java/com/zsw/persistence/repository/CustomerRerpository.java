package com.zsw.persistence.repository;

import com.zsw.data.orm.repository.BaseDao;
import com.zsw.data.orm.repository.support.SimpleDao;
import com.zsw.persistence.entity.Customer;
import org.springframework.stereotype.Repository;

/**
 * @author Shaowei Zhang on 2019/4/21 0:58
 **/
@Repository
public class CustomerRerpository extends SimpleDao<Customer, Long> implements BaseDao<Customer, Long> {
}
