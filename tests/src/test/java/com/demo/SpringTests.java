package com.demo;

import lombok.SneakyThrows;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Map;

/**
 * @author ZhangShaowei on 2021/4/21 13:47
 */
public class SpringTests {

    private Map<String, List<Integer>> map;

    @SneakyThrows
    public static void main(String[] args) {
//        ResolvableType resolvableType = ResolvableType.forField(SpringTests.class.getDeclaredField("map"));
//        System.out.println(resolvableType.getRawClass());
//        System.out.println(resolvableType.getGeneric(0).resolve());
//        System.out.println(resolvableType.getGeneric(1).resolve());
//        System.out.println(resolvableType.getGeneric(1, 0));
//        System.err.println(resolvableType.asMap());

        ResolvableType r = ResolvableType.forClass(Ct.class);
        System.out.println(r.getInterfaces()[0].getGeneric(0));
        System.out.println(r.getInterfaces()[0].getGeneric(1));
    }


    static class Ct implements Converter<String, Integer> {

        @Override
        public Integer convert(String source) {
            return null;
        }
    }

}
