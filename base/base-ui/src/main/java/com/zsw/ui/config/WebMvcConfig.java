package com.zsw.ui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * WebMvcConfig
 *
 * @author ZhangShaowei on 2018/4/26 14:08
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 指定静态资源地址
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}
