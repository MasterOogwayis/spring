package com.base.api;

import com.base.persistence.CustomerRepository;
import com.base.persistence.entity.Customer;
import com.base.persistence.entity.QCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.querydsl.QSort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @author ZhangShaowei on 2021/10/21 13:29
 */
@RestController
@RequestMapping("customer")
public class CustomerApi {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("get")
    public Object get() {

        Customer dave = customerRepository.save(new Customer("Dave", "Matthews"));
        Customer oliver = customerRepository.save(new Customer("Oliver August", "Matthews"));
        Customer carter = customerRepository.save(new Customer("Carter", "Beauford"));

        return Arrays.asList(dave, oliver, carter);
    }


    @GetMapping("findByfirstName")
    public Object findByfirstName(@RequestParam("firstName") String firstName) {
        QCustomer customer = QCustomer.customer;
        return this.customerRepository.findByLastName(firstName, new QSort(customer.firstName.asc()));
    }

    @GetMapping("findByLastName")
    public Object findByLastName(@RequestParam("lastName") String lastName) {
        QCustomer customer = QCustomer.customer;
        return this.customerRepository.findByLastName(lastName, new QSort(customer.lastName.asc()));
    }

    @GetMapping("findAll")
    public Object findAll() {
        return this.customerRepository.findAllByCustomQueryWithStream().collect(Collectors.toList());
    }

    @GetMapping("select")
    public Object select(@RequestParam("name") String name) {
        QCustomer customer = QCustomer.customer;
        List<Customer> customers = this.mongoTemplate.find(Query.query(where("firstName").is(name)), Customer.class);
        return customers;
    }

}
