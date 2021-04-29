package com.demo.spring.dependency.lookup;

import com.demo.spring.dependency.domain.UserHandler;
import com.demo.spring.ioc.overview.domain.SuperUser;
import com.demo.spring.ioc.overview.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author ZhangShaowei on 2021/4/29 13:48
 */
@Slf4j
public class InjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectionDemo.class);


        applicationContext.refresh();


        injectByName(applicationContext);
//        injectByType(applicationContext);


        applicationContext.close();


    }


    public static void injectByName(AnnotationConfigApplicationContext applicationContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(UserHandler.class)
                .setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_NAME)
                .addAutowiredProperty("superUser");
        applicationContext.registerBeanDefinition("userHandler", builder.getBeanDefinition());

        UserHandler userHandler = applicationContext.getBean(UserHandler.class);
        log.info("userHandler: {}", userHandler);
    }


    public static void injectByType(AnnotationConfigApplicationContext applicationContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(UserHandler.class)
                .setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE)
                .addAutowiredProperty("superUser");
        applicationContext.registerBeanDefinition("userHandler", builder.getBeanDefinition());

        UserHandler userHandler = applicationContext.getBean(UserHandler.class);
        log.info("userHandler: {}", userHandler);
    }


    @Bean
    public User user() {
        User user = User.createUser();
        user.setName("user");
        return user;
    }


    @Bean
    public SuperUser superUser() {
        SuperUser superUser = new SuperUser();
        superUser.setName("superUser");
        return superUser;
    }


}
