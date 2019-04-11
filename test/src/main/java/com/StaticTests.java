package com;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;

/**
 * @author ZhangShaowei on 2019/4/3 13:53
 **/
public class StaticTests {

    public static void main(String[] args) {
        AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(Product.class);

        AnnotationAttributes attributes = AnnotationConfigUtils.attributesFor(abd.getMetadata(), DependsOn.class);

        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd, "asd");
        System.out.println("--" + definitionHolder.getAliases());

        System.err.println(attributes);

        abd.setDependsOn(attributes.getStringArray("value"));

        System.err.println(abd);

    }



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
