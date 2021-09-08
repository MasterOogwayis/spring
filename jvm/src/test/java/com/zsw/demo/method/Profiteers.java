package com.zsw.demo.method;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 奸商
 *
 * @author ZhangShaowei on 2021/9/7 14:30
 */
public class Profiteers extends Shop {

    @Override
    public double discount(Double price, Customer customer) {
        if (customer.isVIP()) {
            return price * 价格歧视();
        }
        return super.discount(price, customer);
    }


    public static double 价格歧视() {
        return ThreadLocalRandom.current().nextDouble() + 0.8d;
    }
}
