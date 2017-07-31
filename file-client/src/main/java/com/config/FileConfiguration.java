package com.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import javax.servlet.MultipartConfigElement;

/**
 * @author ZhangShaowei on 2017/7/19 14:43
 */
@Configuration
@ConfigurationProperties(prefix = "zsw.base.config.file", ignoreUnknownFields = true)
public class FileConfiguration {

    /**
     * 单个文件最大大小
     */
    private String maxFileSize;

    /**
     * 总文件最大大小
     */
    private String maxRequestSize;

    /**
     * @return
     */
    @Bean
    MultipartConfigElement createMultipartConfigElement() {
        MultipartConfigFactory mcf = new MultipartConfigFactory();
        /**
         * 设置最大上传文件的大小，默认是1MB
         */
        mcf.setMaxFileSize(this.maxFileSize);
        mcf.setMaxRequestSize(this.maxRequestSize);
        return mcf.createMultipartConfig();
    }


    /**
     * @return
     */
    @Bean
    @Primary
    @Scope("prototype")
    public Encoder multipartFormEncoder() {
        return new SpringFormEncoder();
    }

    /**
     * @return
     */
    @Bean
    public feign.Logger.Level multipartLoggerLevel() {
        return feign.Logger.Level.FULL;
    }

    /**  */
    public void setMaxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    /**  */
    public void setMaxRequestSize(String maxRequestSize) {
        this.maxRequestSize = maxRequestSize;
    }
}
