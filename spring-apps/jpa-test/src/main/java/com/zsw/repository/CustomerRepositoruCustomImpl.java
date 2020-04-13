package com.zsw.repository;

import com.zsw.orm.repository.impl.BaseCustomRepositoryImpl;
import com.zsw.repository.entity.Customer;

import javax.persistence.Query;
import java.util.List;

/**
 * @author ZhangShaowei on 2019/12/26 11:15
 **/
public class CustomerRepositoruCustomImpl extends BaseCustomRepositoryImpl<Customer, Long> implements CustomerRepositoruCustom {
    @Override
    public List<CustomerDto> t1() {
        String sql = "SELECT * FROM t_customer";
        Query nativeQuery = this.getEntityManager().createNativeQuery(sql, CustomerDto.class);
        return nativeQuery.getResultList();
    }
}
