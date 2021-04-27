package com.demo.spring.beans.definition;

import com.demo.spring.beans.config.UserCreatorConfig;
import com.demo.spring.beans.service.TUser;
import com.demo.spring.ioc.overview.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.Map;

/**
 * 创建 bean 的几种方式
 * 1. @Component
 * 2. @Bean
 * 3. BeanDefinition
 * 4. applicationContext.registerBean
 * 5. @import
 * 6. xml 方式这里就不举例了
 *
 * @author ZhangShaowei on 2021/4/26 16:44
 */
@Slf4j
public class ManyTypeToCreateBean {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 1.
        applicationContext.scan(TUser.class.getPackageName());
        // 2.
        applicationContext.register(UserCreatorConfig.class);
        // 3.
        useBeanDefinition(applicationContext);
        // 4.
        applicationContext.registerBean("defaultUser", User.class, () -> {
            User user = new User();
            user.setId(Long.MIN_VALUE);
            user.setName("defaultUser");
            user.setAddress("Earth");
            return user;
        }, bd -> {
//            bd.setPrimary(true);
            log.info("defaultUser BeanDefinition = {}", bd);
        });

        // 5.
        applicationContext.register(ImportConfig.class);

//        AutowireCapableBeanFactory beanFactory = applicationContext.getBeanFactory();
//        User bean = beanFactory.createBean(SuperUser.class);
//        SuperUser superUser = beanFactory.getBean(SuperUser.class);


        // refresh
        applicationContext.refresh();

        Map<String, User> users = applicationContext.getBeansOfType(User.class);
        // users.size() = 5
        users.forEach((k, v) -> {
            log.info("name = {}, value = {}", k, v);
        });

        log.info("alias = {}", Arrays.toString(applicationContext.getAliases("user")));
        // close
        applicationContext.close();
    }


    public static void useBeanDefinition(AnnotationConfigApplicationContext applicationContext) {
        //        BeanDefinition beanDefinition = new RootBeanDgefinition(UserService.class);
//        beanDefinition.getPropertyValues().add("age", 18);
//        beanDefinition.getPropertyValues().add("address", "Earth");
//        beanDefinition.getConstructorArgumentValues().addGenericArumentValue("Name");
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class)
                .addPropertyValue("id", 1L)
                .addPropertyValue("name", "beanDefinitionUser")
                .addPropertyValue("address", "Earth");
        BeanDefinition beanDefinition = builder.getBeanDefinition();
        applicationContext.registerBeanDefinition("user", beanDefinition);
    }


    @Import(User.class)
    public static class ImportConfig {

    }

}
