package com.demo.spring.environment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.convert.ConversionService;

/**
 * @author ZhangShaowei on 2021/11/26 8:59
 */
@SpringBootApplication
public class EnvironmentAppStarter {


//    @Autowired
//    private PropertyEditor propertyEditor;

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(EnvironmentAppStarter.class, args);

        ConversionService conversionService = applicationContext.getBean(ConversionService.class);
        System.out.println(conversionService);

        UserProperties userProperties = applicationContext.getBean(UserProperties.class);

        System.err.println(userProperties);


    }

}
