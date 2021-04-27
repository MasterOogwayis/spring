package com.demo.spring.beans.definition;

import com.demo.spring.beans.factory.DefaultUserFactory;
import com.demo.spring.beans.factory.UserFactory;
import com.demo.spring.beans.factory.UserFactoryBean;
import com.demo.spring.ioc.overview.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.serviceloader.ServiceFactoryBean;
import org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ServiceLoader;

/**
 * @author ZhangShaowei on 2021/4/27 10:04
 */
@Slf4j
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        testServiceLoaderFactoryBean();
//        testServiceLoader();
    }


    /**
     * @see ServiceFactoryBean
     * @see ServiceLoaderFactoryBean
     */
    public static void testServiceLoaderFactoryBean() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(ServiceLoaderFactoryBean.class)
                .addPropertyValue("serviceType", UserFactory.class);
        applicationContext.registerBeanDefinition("userFactoryServiceLoader", builder.getBeanDefinition());
        applicationContext.refresh();

        ServiceLoader<UserFactory> serviceLoader = applicationContext.getBean("userFactoryServiceLoader", ServiceLoader.class);
        serviceLoader.findFirst().map(UserFactory::getObject).ifPresent(System.out::println);

        applicationContext.close();


    }

    public static void testServiceLoader() {
        ServiceLoader<UserFactory> load = ServiceLoader.load(UserFactory.class);
        load.stream()
                .map(ServiceLoader.Provider::get)
                .map(UserFactory::getObject)
                .forEach(System.out::println);

    }


    public static void testFactoryBean() {
        BeanDefinitionBuilder builder =
                BeanDefinitionBuilder.rootBeanDefinition(UserFactoryBean.class);
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.registerBeanDefinition("user", builder.getBeanDefinition());

        applicationContext.refresh();

        Object user = applicationContext.getBean("user");

        log.info("UserFactoryBean: {}", user);

        applicationContext.close();
    }


    /**
     * 动态构造
     */
    public static void testFactory() {
        BeanDefinitionBuilder builder =
                BeanDefinitionBuilder.rootBeanDefinition(DefaultUserFactory.class);
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.registerBeanDefinition("userFactory", builder.getBeanDefinition());

        BeanDefinitionBuilder userBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class)
                .setFactoryMethodOnBean("getObject", "userFactory");

        applicationContext.registerBeanDefinition("userByInstanceMethod", userBuilder.getBeanDefinition());

        applicationContext.refresh();

        Object user = applicationContext.getBean("userByInstanceMethod");

        log.info("user = {}", user);

        applicationContext.close();


    }


    /**
     * 静态构造
     */
    public static void testFactoryMethod() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class)
                .setFactoryMethod("createUser");
        BeanDefinition beanDefinition = builder.getBeanDefinition();
        applicationContext.registerBeanDefinition("user", beanDefinition);
        applicationContext.refresh();

        User user = applicationContext.getBean(User.class);

        log.info("User = {}", user);

        applicationContext.close();
    }


}
