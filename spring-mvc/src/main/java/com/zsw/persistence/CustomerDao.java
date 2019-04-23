package com.zsw.persistence;

import com.zsw.data.orm.dao.support.SimpleDao;
import com.zsw.persistence.entity.Customer;
import org.springframework.stereotype.Repository;

/**
 * @author ZhangShaowei on 2019/4/22 16:42
 **/
@Repository
public class CustomerDao extends SimpleDao<Customer, Long> {
}
