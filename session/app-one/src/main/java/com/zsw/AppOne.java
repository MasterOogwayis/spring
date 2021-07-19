package com.zsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;

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

    /**
     * @return
     */
//    @Bean("springSessionDefaultRedisSerializer")
//    public RedisSerializer redisValueSerializer() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
//        return new GenericJackson2JsonRedisSerializer(objectMapper);
//    }

}
