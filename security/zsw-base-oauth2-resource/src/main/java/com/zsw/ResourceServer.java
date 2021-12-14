package com.zsw;

import com.zsw.base.oauth2.annotation.EnableZswResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangShaowei on 2020/9/11 15:28
 */
@EnableZswResourceServer
@SpringBootApplication
public class ResourceServer {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(ResourceServer.class, args);
    }

    /**
     * 跨域
     *
     * @return
     */
//    @Bean
//    public FilterRegistrationBean<Filter> ajaxCorsFilter() {
//        val source = new UrlBasedCorsConfigurationSource();
//        val config = new CorsConfiguration();
//        // 允许cookies跨域
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        config.setMaxAge(18000L);
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>(new CorsFilter(source));
//        // 必须在 security 前
//        registrationBean.setOrder(DEFAULT_FILTER_ORDER - 100);
//        return registrationBean;
//    }

}
