package com.demo.querydsl;

import com.querydsl.jpa.impl.JPAInsertClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.zsw.peprsistence.entity.Customer;
import com.zsw.peprsistence.entity.QCustomer;

/**
 * @author ZhangShaowei on 2021/11/19 15:32
 */
public class QuerydslTests {

    public static void main(String[] args) {
        JPAQuery<Object> query = new JPAQuery<>();
        QCustomer customer = QCustomer.customer;
        JPAQuery<Customer> q = query.select(customer)
                .from(customer)
                .where(customer.name.eq("name").and(customer.id.gt(1).or(customer.age.lt(18))));
        System.out.println(q.toString());

        JPAInsertClause clause = new JPAInsertClause(null, customer);
        JPAInsertClause insert = clause.columns(customer.name, customer.age).values("name", 18);
        System.out.println(insert);

    }

}
