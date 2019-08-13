package com.zsw.config;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import feign.form.spring.converter.SpringManyMultipartFilesReader;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangShaowei on 2017/7/19 14:43
 * <p>
 * https://github.com/OpenFeign/feign-form
 */
@Configuration
public class FileConfiguration {


    public class MultipartSupportConfig {

        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }
    }


    public class ClientConfiguration {

        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Decoder feignDecoder() {
            List<HttpMessageConverter<?>> springConverters =
                    messageConverters.getObject().getConverters();

            List<HttpMessageConverter<?>> decoderConverters = new ArrayList<>(springConverters.size() + 1);

            decoderConverters.addAll(springConverters);
            decoderConverters.add(new SpringManyMultipartFilesReader(4096));

            HttpMessageConverters httpMessageConverters = new HttpMessageConverters(decoderConverters);

            return new SpringDecoder(() -> httpMessageConverters);
        }
    }
}