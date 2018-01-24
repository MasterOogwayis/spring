package com.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * This is a Mvc configuration
 *
 * @author ZhangShaowei on 2018/1/17 10:40
 **/
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    /**
     * @PARAM REGISTRY
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/loading").setViewName("loading");
//        registry.addViewController("/login").setViewName("login");
    }


}
