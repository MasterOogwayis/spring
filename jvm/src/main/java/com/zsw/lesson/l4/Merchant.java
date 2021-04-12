package com.zsw.lesson.l4;

/**
 * @author ZhangShaowei on 2021/4/9 17:25
 */
public class Merchant {

    public Number actionPrice(double price, Customer customer) {
        if (customer.isVIP()) {
            return price * 1.1;
        }
        return price;
    }

}
