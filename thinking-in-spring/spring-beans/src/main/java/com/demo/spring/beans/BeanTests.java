package com.demo.spring.beans;

import com.demo.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ZhangShaowei on 2021/4/27 13:56
 */
public class BeanTests {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class)
                .addPropertyValue("age", 32)
                .addPropertyValue("address", "Mars")
                .addPropertyValue("name", "UserName")
                .setInitMethodName("init");
        applicationContext.registerBeanDefinition("user", builder.getBeanDefinition());
        applicationContext.refresh();

        System.out.println(applicationContext.getBean(User.class));

        applicationContext.close();



    }

}
