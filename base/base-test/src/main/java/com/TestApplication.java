package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ZhangShaowei on 2019/6/21 11:26
 **/
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }


    /**
     * 跨域
     *
     * @return WebMvcConfigurer
     */
//    @Bean
//    public FilterRegistrationBean<Filter> ajaxCorsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        // 请求常用的三种配置，*代表允许所有，也可以自定义属性（比如 header 只能带什么，只能是 post 方式等）
//        corsConfiguration.addAllowedOrigin("*");
//        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setMaxAge(3600L);
//
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
//        //*****这里设置了优先级*****
//        bean.setOrder(1);
//        return bean;
//    }
//    @Bean
//    public WebMvcConfigurer originWebMvcConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowedHeaders("*")
//                        .allowedMethods("*")
//                        .allowCredentials(true)
//                        .maxAge(3600);
//            }
//        };

//    }
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public Filter ajaxCorsFilter() {
//        return new Filter() {
//            @Override
//            public void doFilter(ServletRequest httpRequest, ServletResponse httpResponse, FilterChain chain) throws IOException, ServletException {
//                HttpServletResponse response = (HttpServletResponse) httpResponse;
//                HttpServletRequest request = (HttpServletRequest) httpRequest;
//                response.setHeader("Access-Control-Allow-Origin", "*");
//                response.setHeader("Access-Control-Allow-Methods", "*");
//                response.setHeader("Access-Control-Allow-Headers", "authorization,Authorization,DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type");
//                response.setHeader("Access-Control-Max-Age", "3600");
//                response.setHeader("Access-Control-Allow-Credentials", "true");
//
//                if (RequestMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
//                    response.setStatus(HttpServletResponse.SC_OK);
//                } else {
//                    chain.doFilter(request, response);
//                }
//
//            }
//        };
//    }

}
