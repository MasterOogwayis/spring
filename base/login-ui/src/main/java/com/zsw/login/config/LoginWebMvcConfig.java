package com.zsw.login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * WebMvcConfig
 *
 * @author ZhangShaowei on 2018/4/26 14:08
 **/
@Configuration
public class LoginWebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 添加登录 视图控制
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login/login");
    }

}
