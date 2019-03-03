package com.demo;

import lombok.SneakyThrows;

import javax.script.ScriptEngineManager;

/**
 * @author ZhangShaowei on 2019/2/27 14:15
 **/
public class StaticTests {


    @SneakyThrows
    public static void main(String[] args) {
//        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();

        Class<?> clazz = Class.forName("com.demo.Dto");
        Dto dto = (Dto) clazz.newInstance();
        dto.setName("name");
        System.out.println(dto);
    }


}
