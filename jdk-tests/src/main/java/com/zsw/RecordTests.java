package com.zsw;

/**
 * @author ZhangShaowei on 2020/10/23 9:52
 */
public class RecordTests {

    public static void main(String[] args) {
        Customer customer = new Customer("zsw", 18, "earth");
        System.out.println(customer);
        System.err.println(customer.age());
    }

}
