package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * @author ZhangShaowei on 2017/9/12 16:23
 */
//@Configuration
@SpringBootApplication
public class UserApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApiApplication.class, args);
    }


    /**
     * @return
     */
//    @Bean
//    public FilterRegistrationBean registerOpenEntityManagerInViewFilter(){
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        OpenEntityManagerInViewFilter filter = new OpenEntityManagerInViewFilter();
//        registrationBean.setFilter(filter);
//        registrationBean.setOrder(5);
//        return registrationBean;
//    }

}
