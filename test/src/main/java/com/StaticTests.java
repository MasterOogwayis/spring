package com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2019/4/3 13:53
 **/
public class StaticTests {

    public static void main(String[] args) {

        Field[] fields = TestDto.class.getDeclaredFields();

        for (Field field : fields) {
            System.out.println(field.getName() + ": static=" + Modifier.isStatic(field.getModifiers()));
            System.out.println(field.getName() + ": final=" + Modifier.isFinal(field.getModifiers()));
        }


    }



}
