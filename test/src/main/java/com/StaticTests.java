package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2019/4/3 13:53
 **/
@Slf4j
public class StaticTests {

    public static void main(String[] args) {
        Stream.of(Children.class.getDeclaredFields()).forEach(System.out::println);

    }


//    public static void main(String[] args) {
//        AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(Product.class);
//
//        AnnotationAttributes attributes = AnnotationConfigUtils.attributesFor(abd.getMetadata(), DependsOn.class);
//
//        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd, "asd");
//        System.out.println("--" + definitionHolder.getAliases());
//
//        System.err.println(attributes);
//
//        abd.setDependsOn(attributes.getStringArray("value"));
//
//        System.err.println(abd);
//
//    }


    @Description("asd")
    @Role(1)
    @DependsOn("testApp")
    @Lazy()
    @Primary
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
    @Service(value = "product")
    class Product {

    }

}
