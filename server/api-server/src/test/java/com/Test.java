package com;

import com.zsw.persistence.bean.Customer;
import com.zsw.persistence.bean.User;
import com.zsw.service.customer.CustomerService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author ZhangShaowei on 2017/9/18 10:03
 */

public class Test {

    public static void main(String[] args) {


//        Optional<Customer> customer = get();
//
//
//        if (customer.isPresent()) {
//            System.out.println(customer.get());
//
//        }

//        Optional<Customer> customer = get();
//        Customer c = customer.orElseGet(Customer::new);
//        String nameStr = customer.map(Customer::getName).map(String::toUpperCase).orElse("There is no data");
//        System.err.println(nameStr);
//        list.stream().filter(optional::isPresent).



//        hello("Hello", Test::callback);


        List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6");

        List<Integer> l = list.stream().map(Integer::valueOf).collect(Collectors.toList());

        System.out.println(l);


    }


    public static void hello(String message, Consumer<String> callback) {
        callback.accept(message);
    }


    public static void callback(String str) {
        System.out.println(str);
    }


    private static Optional<Customer> get() {
        Customer customer = new Customer();
//        customer.setName("zsw");

        return Optional.of(customer);
    }



}
