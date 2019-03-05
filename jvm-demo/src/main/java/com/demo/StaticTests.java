package com.demo;

import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZhangShaowei on 2019/2/27 14:15
 **/
public class StaticTests {


    @SneakyThrows
    public static void main(String[] args) {
//        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();

        List<Integer> list = Arrays.asList(0x6a, 0x61, 0x76, 0x61, 0x2f, 0x6c, 0x61, 0x6e, 0x67, 0x2f, 0x4f, 0x62, 0x6a, 0x65, 0x63, 0x74);
        String str = list.stream().map(i -> {
            int j = i;
            char c = (char) j;
            return String.valueOf(c);
        }).collect(Collectors.joining());

        System.out.println(str);
    }


}
