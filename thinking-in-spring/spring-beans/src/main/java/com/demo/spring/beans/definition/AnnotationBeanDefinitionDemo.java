package com.demo.spring.beans.definition;

import com.demo.spring.beans.config.UserCreatorConfig;
import com.demo.spring.ioc.overview.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ZhangShaowei on 2021/4/26 16:22
 */
@Slf4j
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(UserCreatorConfig.class);


        log.info("BeanDefinitionCount = {}", applicationContext.getBeanDefinitionCount());

        // refresh
        applicationContext.refresh();

//        BeanDefinition beanDefinition = applicationContext.getBeanDefinition("user");
//        log.info("user BeanDefinition = {}", beanDefinition);

        applicationContext.registerBean("defaultUser", User.class, () -> {
            User user = new User();
            user.setId(Long.MIN_VALUE);
            user.setName("defaultUser");
            user.setAddress("Earth");
            return user;
        }, bd -> {
            bd.setPrimary(true);
            log.info("defaultUser BeanDefinition = {}", bd);
        });

        User user = applicationContext.getBean(User.class);
        log.info("user = {}", user);


        // close
        applicationContext.close();

    }

}
