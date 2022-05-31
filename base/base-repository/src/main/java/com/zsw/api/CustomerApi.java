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

    @GetMapping("update")
    public Object update(@RequestParam("name") String name, @RequestParam("id") Long id) {
        QCustomer customer = QCustomer.customer;
        return this.repository.updateClause(customer)
                .set(customer.name, name)
                .where(customer.id.eq(id))
                .execute();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
//        List<Customer> list = new ArrayList<>(100);
//        QCustomer customer = QCustomer.customer;
//        for (int i = 0; i < 100; i++) {
//            InsertClause<?> clause = this.repository.insertClause(customer);
//            clause.columns(customer.name, customer.address, customer.idNo, customer.age)
//                    .values("name" + i, (((i & 1) == 1) ? "Earth" : "Mars"), ("510199232323232323" + i), i)
//                    .execute();
//            Customer customer = Customer.builder()
//                    .name("name" + i)
//                    .address()
//                    .idNo("510199232323232323" + i)
//                    .age(i)
//                    .build();
    }

//        this.repository.saveAllAndFlush(list);
//    }
}
