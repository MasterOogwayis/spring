package com.demo.spring.i18n;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import static org.springframework.context.support.AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME;

/**
 * @author ZhangShaowei on 2021/6/28 13:48
 */
@EnableAutoConfiguration
public class CustomizedMessageSourceBeanDemo {


    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(CustomizedMessageSourceBeanDemo.class)
                .web(WebApplicationType.NONE)
                .run(args);


        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        if (applicationContext.containsBean(MESSAGE_SOURCE_BEAN_NAME)) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(MESSAGE_SOURCE_BEAN_NAME);
            System.out.println(beanDefinition);

            MessageSource messageSource = applicationContext.getBean(MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
            System.out.println(messageSource);
        }

        applicationContext.close();
    }


    /**
     * 在 Spring Boot 场景中，Primary Configuration Sources(Classes) 高于 *AutoConfiguration
     *
     * @return
     */
    @Bean(MESSAGE_SOURCE_BEAN_NAME)
    public MessageSource messageSource() {
        return new ReloadableResourceBundleMessageSource();
    }

}
