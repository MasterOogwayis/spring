package com.zsw.config;

import com.zsw.encoder.FeignSpringFormEncoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * @author ZhangShaowei on 2017/7/19 14:43
 */
@Configuration
public class FileConfiguration {

    /**
     *
     */
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;


    /**
     * @return
     */
    @Bean
    @Primary
    @Scope("prototype")
    public Encoder multipartFormEncoder() {
        return new FeignSpringFormEncoder(messageConverters);
    }

    /**
     * @return
     */
    @Bean
    public feign.Logger.Level multipartLoggerLevel() {
        return feign.Logger.Level.FULL;
    }


}
