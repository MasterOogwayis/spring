package com.zsw.spring.demo.validation;

import com.demo.spring.ioc.overview.domain.User;
import lombok.SneakyThrows;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2021/6/30 13:29
 */
public class JavaBeansDemo {

    @SneakyThrows
    public static void main(String[] args) {
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);
        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {
//                    propertyDescriptor.getReadMethod();
//                    propertyDescriptor.getWriteMethod();
                    System.out.println(propertyDescriptor);
                });

        Stream.of(beanInfo.getMethodDescriptors()).forEach(System.out::println);

    }

}
