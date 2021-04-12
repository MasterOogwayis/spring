package com.zsw.lesson.l4;

/**
 * @author ZhangShaowei on 2021/4/9 17:25
 */
public class MerchantV2<T extends Customer> {

    public Number actionPrice(double price, T customer) {
        if (customer.isVIP()) {
            return price * 1.1;
        }
        return price;
    }

}
