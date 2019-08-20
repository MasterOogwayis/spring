package com;

import com.demo.*;
import org.junit.Test;

import java.util.Collections;

/**
 * @author ZhangShaowei on 2019/6/20 15:31
 **/
public class MapStructTests {

    @Test
    public void test() {

        Product product = Product.builder().id(1L).name("水果").build();
        Customer customer = Customer.builder().id(2L).name("zsw")
                .products(Collections.singletonList(product)).build();

        CustomerDto customerDto = CustomerMapper.INSTANCE.toDto(customer);

        System.out.println(customerDto);

    }

}
