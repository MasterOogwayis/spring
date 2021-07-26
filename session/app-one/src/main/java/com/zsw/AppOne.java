package com.zsw;

import com.zsw.serializer.ProtostuffRedisSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.DefaultCookieSerializerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * spring session data redis 序列化 RedisTemplate
 *
 * @author ZhangShaowei on 2021/7/8 13:48
 * @see RedisHttpSessionConfiguration#createRedisTemplate()
 * @see RedisHttpSessionConfiguration#setDefaultRedisSerializer(RedisSerializer)
 */
@EnableWebSecurity
//@EnableRedisHttpSession
@SpringBootApplication
public class AppOne {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(AppOne.class, args);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DefaultCookieSerializerCustomizer cookieSerializerCustomizer() {
        return cookieSerializer -> cookieSerializer.setUseBase64Encoding(Boolean.FALSE);
    }

//    /**
//     * @return
//     */
//    @Bean("springSessionDefaultRedisSerializer")
//    public RedisSerializer redisValueSerializer() {
//        return new ProtostuffRedisSerializer();
//    }

}
