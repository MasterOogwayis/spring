package com.demo;

import lombok.SneakyThrows;
import org.springframework.core.ResolvableType;

import java.util.List;
import java.util.Map;

/**
 * @author ZhangShaowei on 2021/8/3 13:11
 */
public class ResolvableTypeDemo {

    private Map<String, List<Integer>> map;

    public static void main(String[] args) {
        test1();
    }

    @SneakyThrows
    public static void test2() {
        ResolvableType resolvableType = ResolvableType.forField(ResolvableTypeDemo.class.getDeclaredField("map"));
        // Map
        System.out.println(resolvableType.getRawClass());
        // String
        System.out.println(resolvableType.getGeneric(0).resolve());
        // List
        System.out.println(resolvableType.getGeneric(1).resolve());
        // Integer
        System.out.println(resolvableType.getGeneric(1, 0).resolve());

        System.out.println(resolvableType.asCollection().resolve());


    }

    public static void test1() {
        // 工厂创建
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);
        // Arraylist
        System.out.println("SuperType:" + resolvableType.getSuperType());
        // AbstractList
        System.out.println("s s type:" + resolvableType.getSuperType().getSuperType());

        // 获取 raw type
        System.out.println(resolvableType.asCollection().resolve());

        // 获取泛型参数类型
        System.out.println(resolvableType.asCollection().resolveGeneric(0));
    }

}
