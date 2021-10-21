package com.base.persistence.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2021/10/21 10:14
 */
@Data
@Document("customer")
@RequiredArgsConstructor
public class Customer implements Serializable {

    private static final long serialVersionUID = -127689712321300860L;
    @Id
    private String id;

    @Field
    private String firstName;

    @Field
    private String lastName;

//    private Address address;


    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
