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
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2019/4/3 13:53
 **/
public class StaticTests {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("1", "2", "3");

        System.out.println(list.stream().collect(Collectors.joining("", "", "")));



    }



}
