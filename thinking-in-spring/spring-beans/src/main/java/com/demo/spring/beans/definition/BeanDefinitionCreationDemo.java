package com.demo.spring.beans.definition;

import com.demo.spring.beans.service.Customer;
import com.demo.spring.beans.service.UserService;
import com.demo.spring.ioc.overview.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author ZhangShaowei on 2021/4/26 14:11
 */
@Slf4j
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {
        testFactoryMethod();
//        testBeanDefinition();
    }


    public static void testFactoryMethod() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Customer.class)
                .setFactoryMethod("getInstance");
        BeanDefinition beanDefinition = builder.getBeanDefinition();
        applicationContext.registerBeanDefinition("customer", beanDefinition);
        applicationContext.refresh();

        // error
        Customer customer = applicationContext.getBean(Customer.class);

        log.info("customer = {}", customer);

        applicationContext.close();
    }


    public static void testBeanDefinition() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//        BeanDefinition beanDefinition = new RootBeanDgefinition(UserService.class);
//        beanDefinition.getPropertyValues().add("age", 18);
//        beanDefinition.getPropertyValues().add("address", "Earth");
//        beanDefinition.getConstructorArgumentValues().addGenericArumentValue("Name");
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(UserService.class)
                .addPropertyValue("age", 18)
                .addPropertyValue("address", "Earth")
                .addConstructorArgValue("Name")
                .setInitMethodName("init");
        BeanDefinition beanDefinition = builder.getBeanDefinition();

        applicationContext.registerBeanDefinition("userService", beanDefinition);
        // IE明
        applicationContext.registerAlias("userService", "defaultUserService");

        builder = BeanDefinitionBuilder.genericBeanDefinition(User.class)
                .addPropertyValue("age", 32)
                .addPropertyValue("address", "Mars")
                .addPropertyValue("name", "UserName");
        beanDefinition = builder.getBeanDefinition();

        applicationContext.registerBeanDefinition("user", beanDefinition);
        applicationContext.refresh();

        UserService userService = applicationContext.getBean(UserService.class);
        log.info("UserService = {}", userService);
        userService = (UserService) applicationContext.getBean("defaultUserService");
        log.info("defaultUserService = {}", userService);
        applicationContext.close();
    }


    public static void testBeanExecutor() throws ExecutionException, InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TaskExecutionAutoConfiguration.class);
        applicationContext.refresh();
        ThreadPoolTaskExecutor executor = applicationContext.getBean(ThreadPoolTaskExecutor.class);
        Future<String> submit = executor.submit(() -> "Hello World");
        System.err.println(submit.get());
        applicationContext.close();
    }


}
