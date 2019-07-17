package com.zsw.orm.utils;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.BeanUtilsSpring;
import org.springframework.cglib.beans.BeanCopier;

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

        BeanCopier beanCopier = BeanCopier.create(TestDto.class, TestDto.class, false);
        for (int i = 0; i < 1000000; i++) {
            beanCopier.copy(dto, d, null);
        }
        System.err.println("cglib: " + (System.currentTimeMillis() - timer));
        timer = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            BeanUtils.copyProperties(dto, d);
        }
        System.err.println("mine: " + (System.currentTimeMillis() - timer));
        timer = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            org.springframework.beans.BeanUtils.copyProperties(dto, d);
        }

        System.out.println("spring: " + (System.currentTimeMillis() - timer));

    }


}
