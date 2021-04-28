package com.demo.spring.dependency.lookup;

import com.demo.spring.ioc.overview.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static com.zsw.utils.ExceptionHandler.handleException;

/**
 * @author ZhangShaowei on 2021/4/28 14:57
 */
@Slf4j
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        applicationContext.registerBeanDefinition("nullUser", builder.getBeanDefinition());

        applicationContext.refresh();

        displayObjectFactoryGetObject(applicationContext);
        displayBeanFactoryGetBean(applicationContext);

        applicationContext.close();

    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        beanProvider.stream().forEach(System.out::println);
        handleException(() -> {
            log.info("objectProvider = {}, getIfAvailable = {}", beanProvider, beanProvider.getIfAvailable());
        });
        handleException(() -> {
            log.info("objectProvider = {}, getObject = {}", beanProvider, beanProvider.getObject());
        });

    }

    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        handleException(() -> log.info("user: {}", beanFactory.getBean(User.class)));
    }


    @Bean
    @Primary
    public User user() {
        User user = User.createUser();
        user.setId((long) Integer.MAX_VALUE);
        user.setName("Name");
        user.setAddress("Mars");
        return user;
    }


}
