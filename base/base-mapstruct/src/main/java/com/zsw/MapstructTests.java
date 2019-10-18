package com.zsw;

import com.zsw.demo.Customer;
import com.zsw.demo.CustomerDto;
import com.zsw.demo.Product;
import com.zsw.dto.support.mapstruct.CustomerMapper;

/**
 * @author ZhangShaowei on 2019/10/11 10:46
 **/
public class MapstructTests {


    public static void main(String[] args) {

        CustomerMapper customerMapper = CustomerMapper.INSTANCE;

        Customer customer = Customer.builder()
                .id(1L).name("zsw")
                .product(Product.builder().id(2L).name("产品").build())
                .build();

        CustomerDto customerDto = customerMapper.entityToDto(customer);
        System.out.println(customerDto);


    }

}
