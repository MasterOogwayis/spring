package com.zsw.lesson.l4;

/**
 * @author ZhangShaowei on 2021/4/9 17:26
 */
public class NaiveMerchant extends Merchant {

    @Override
    public Double actionPrice(double price, Customer customer) {
        return price * 0.5;
    }

}
