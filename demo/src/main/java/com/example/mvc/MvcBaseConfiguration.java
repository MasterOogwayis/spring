package com.example.mvc;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.Filter;

@Configuration
@EnableWebMvc
@ServletComponentScan(basePackages = { "com" })
public class MvcBaseConfiguration extends WebMvcConfigurerAdapter {



  @Bean
  public ViewResolver getViewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/");
    resolver.setSuffix(".jsp");
    return resolver;
  }
  @Bean
  public BeanPostProcessor getBeanPostProcessor(){
    StringHttpMessageBeanPostProcessor s = new StringHttpMessageBeanPostProcessor();
    s.setEncode("UTF-8");
    return s;
  }


  @Bean
  public Filter characterEncodingFilter() {
    CharacterEncodingFilter characterEncodingFilter =new CharacterEncodingFilter();
    characterEncodingFilter.setEncoding("UTF-8");
    characterEncodingFilter.setForceEncoding(true);
    return characterEncodingFilter;
  }

  @Bean
  public Filter hiddenHttpMethodFilter(){
    HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
    return filter;
  }

}