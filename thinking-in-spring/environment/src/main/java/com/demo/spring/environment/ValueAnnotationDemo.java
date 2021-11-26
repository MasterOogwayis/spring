package com.demo.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.convert.ConversionService;

import java.beans.PropertyEditor;

/**
 * @author ZhangShaowei on 2021/11/24 9:13
 */
public class ValueAnnotationDemo {

    @Value("${user.name}")
    private String username;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ValueAnnotationDemo.class);
        applicationContext.scan(ValueAnnotationDemo.class.getPackage().getName());
        applicationContext.refresh();

        ValueAnnotationDemo bean = applicationContext.getBean(ValueAnnotationDemo.class);
        System.out.println(bean.username);

//        ConversionService conversionService = applicationContext.getBean(ConversionService.class);
//        PropertyEditor propertyEditor = applicationContext.getBean(PropertyEditor.class);

        UserProperties userProperties = applicationContext.getBean(UserProperties.class);

        System.err.println(userProperties);

        applicationContext.close();


    }

}
