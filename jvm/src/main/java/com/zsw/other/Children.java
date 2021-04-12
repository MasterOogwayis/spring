package com.zsw.other;

import com.zsw.lesson.l4.Customer;

/**
 * @author ZhangShaowei on 2021/4/12 11:03
 */
public class Children implements Parent<Integer> {
    @Override
    public Integer t(Integer number) {
        return this.parse(number);
    }

    public void say(Customer customer, Number number) {
        if (customer.isVIP()) {
            println(number);
        }
        parse(number);
        // 对于有 final 修饰的非私有方法，使用静态绑定
        parse1(number);
    }

    public Integer parse(Number number) {
        return number.intValue();
    }

    public final Integer parse1(Number number) {
        return number.intValue();
    }

    public static void println(Object obj) {
        System.out.println(obj);
    }


}
