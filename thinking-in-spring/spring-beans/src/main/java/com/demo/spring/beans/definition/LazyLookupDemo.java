package com.demo.spring.beans.definition;

import com.demo.spring.beans.config.UserCreatorConfig;
import com.demo.spring.ioc.overview.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ZhangShaowei on 2021/4/28 11:25
 */
@Slf4j
public class LazyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(UserCreatorConfig.class);

        applicationContext.refresh();


        ObjectProvider<User> userProvider = applicationContext.getBeanProvider(User.class);

        userProvider.ifAvailable(user -> {
            log.info("user: {}", user);
        });

        User beanOfType = BeanFactoryUtils.beanOfType(applicationContext, User.class);
        log.info("BeanFactoryUtils.beanOfType : {}", beanOfType);

        applicationContext.close();
    }

}
