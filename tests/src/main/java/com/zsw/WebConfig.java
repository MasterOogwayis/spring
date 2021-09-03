package com.zsw;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ZhangShaowei on 2021/6/18 11:45
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/file/**").addResourceLocations("file:/data/newbee/upload/");
//        registry.addResourceHandler("/path/**").addResourceLocations("classpath:/static/");
//    }

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        resolvers.add(new RequestHeaderCustomMethodArgumentResolver());
//    }
}
