package com.zsw.api;

import com.querydsl.jpa.impl.JPAQuery;
import com.zsw.peprsistence.entity.Customer;
import com.zsw.peprsistence.entity.QCustomer;
import com.zsw.peprsistence.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangShaowei on 2021/11/18 10:08
 */
@AllArgsConstructor
@RequestMapping("customer")
@RestController
public class CustomerApi implements InitializingBean {

    private final CustomerRepository repository;


    @GetMapping("find")
    public Object findByName(@RequestParam("name") String name) {
        QCustomer customer = QCustomer.customer;
        JPAQuery<Customer> query = this.repository.queryDsl();
        return query.select(customer)
                .from(customer)
                .where(customer.name.eq(name))
                .fetch();
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        List<Customer> list = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            Customer customer = Customer.builder()
                    .name("name" + i)
                    .address(((i & 1) == 1) ? "Earth" : "Mars")
                    .idNo("510199232323232323" + i)
                    .age(i)
                    .build();
            list.add(customer);
        }

        this.repository.saveAllAndFlush(list);
    }
}
