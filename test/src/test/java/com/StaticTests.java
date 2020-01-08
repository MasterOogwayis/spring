package com;

import com.mapper.Customer;
import com.mapper.CustomerDto;
import com.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author ZhangShaowei on 2019/10/9 10:03
 **/
@Slf4j
//@RunWith(Parameterized.class)
public class StaticTests {

//    @Parameterized.Parameters
//    public static Object[][] data() {
//        return new Object[10][0]; // repeat count which you want
//    }


    public static void main(String[] args) {
        System.out.println(StaticTests.class.getName());
    }


    private static final CustomerMapper MAPPER = CustomerMapper.INSTANCE;


    @Test
    public void testEncode() {
        String text = "12aksjkahksfdaksdjakfkahksdjasdasd3";
        System.out.println(Base64.encodeBase64URLSafeString(text.getBytes(UTF_8)));
        System.err.println(java.util.Base64.getEncoder().encodeToString(text.getBytes(UTF_8)));
    }

    @Test
    public void testDate() {
        LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
        LocalDate now = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        System.out.println(localDate.equals(now));



    }


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


    @Test
    public void test() {
        System.err.println(StringUtils.getFilenameExtension("/data/spring/zsw.png"));
    }


    public static String fenToYuanHalfUp(Number price) {
        BigDecimal number = BigDecimal.valueOf(price.doubleValue())
                .divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);

        String s = number.toPlainString();
        return number.toString();
    }

}
