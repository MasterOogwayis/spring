package com.boot;

import com.boot.interceptor.SasAllowOriginInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author ZhangShaowei on 2017/7/11 16:42
 */
@SpringBootApplication
public class SwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }


//    @Bean
//    public HandlerInterceptorAdapter handlerInterceptorAdapter(){
//        return new HiddenHttpMethodFilter();
//    }

}
