package com;

import com.mapper.Customer;
import com.mapper.CustomerDto;
import com.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * @author ZhangShaowei on 2019/10/9 10:03
 **/
@Slf4j
@RunWith(Parameterized.class)
public class StaticTests {

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[10][0]; // repeat count which you want
    }

    private static final CustomerMapper MAPPER = CustomerMapper.INSTANCE;

    @Test
    public void testCopy() {

        int times = 1000;

        Customer customer = Customer.builder().id(1L).name("Mr Zhang").age(18).address("Somewhere in space").build();

        CustomerDto customerDto = new CustomerDto();
        long timer = System.nanoTime();
        for (int i = 0; i < times; i++) {
            BeanUtils.copyProperties(customer, customerDto);
        }
        log.debug("BeanUtils.copyProperties cust {}ns", System.nanoTime() - timer);
        timer = System.nanoTime();

        for (int i = 0; i < times; i++) {
            FastCopy.copyProperties(customerDto, customer);
        }
        log.debug("javassist.copyProperties cust {}ns", System.nanoTime() - timer);
        timer = System.nanoTime();

        for (int i = 0; i < times; i++) {
            MAPPER.copyProperties(customerDto, customer);
        }
        log.debug("MAPPER.copyProperties cust {}ns", System.nanoTime() - timer);

    }


    public static String fenToYuanHalfUp(Number price) {
        BigDecimal number = BigDecimal.valueOf(price.doubleValue())
                .divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);

        String s = number.toPlainString();
        return number.toString();
    }

}
