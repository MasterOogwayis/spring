package com.zsw.orm.utils;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.BeanUtils;

/**
 * @author ZhangShaowei on 2019/7/15 16:58
 **/
@RunWith(Parameterized.class)
public class BeanUtilsTests {

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[2][0]; // repeat count which you want
    }

    @Test
    @SneakyThrows
    public void test() {
        long timer = System.currentTimeMillis();
        TestDto dto = new TestDto("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"
                , "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
        TestDto d = new TestDto();
        for (int i = 0; i < 1000000; i++) {
            BeanUtils.copyProperties(dto, d);
        }
        System.err.println(System.currentTimeMillis() - timer);
//        timer = System.currentTimeMillis();
//        for (int i = 0; i < 1000000; i++) {
//            org.springframework.beans.BeanUtils.copyProperties(dto, d);
//        }
//
//        System.out.println(System.currentTimeMillis() - timer);

    }


}
