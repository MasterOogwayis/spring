package com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author ZhangShaowei on 2019/10/9 10:03
 **/
@Slf4j
public class StaticTests {

    private static final Gson GSON = new GsonBuilder().create();
    public static final String DATE_FORMAT = "yyyyMMdd";

    private static final CustomerDtoMapper MAPPER = CustomerDtoMapper.INSTANCE;



    @Test
    public void test() {
        List<String> list = new ArrayList<>();

        list.addAll(Collections.emptyList());
    }


    @Test
    public void testMapping() {
        Dto source = Dto.builder().amount(12D)
                .price(34D)
                .age(18D)
                .date1(new Date())
                .date2(new Date())
                .amount(10D)
                .build();
        CustomerDto from = MAPPER.from(source, 20D);
        System.out.println(from);

    }


    public static String fenToYuanHalfUp(Number price) {
        BigDecimal number =  BigDecimal.valueOf(price.doubleValue())
                        .divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);

        String s = number.toPlainString();
        return number.toString();
    }

}
