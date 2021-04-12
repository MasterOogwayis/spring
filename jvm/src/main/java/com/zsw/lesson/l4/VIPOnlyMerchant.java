package com.zsw.lesson.l4;

/**
 * @author ZhangShaowei on 2021/4/9 17:31
 */
public class VIPOnlyMerchant extends MerchantV2<VIP> {

    @Override
    public Double actionPrice(double price, VIP customer) {
        return price * 0.5;
    }

}
