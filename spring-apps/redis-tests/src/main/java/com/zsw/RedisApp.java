package com.zsw;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author ZhangShaowei on 2020/7/30 11:14
 */
@SpringBootApplication
public class RedisApp {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(RedisApp.class, args);
    }


    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            RedisConnectionFactory connectionFactory, RedisTemplate<String, String> redisTemplate) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(
                new RedisMessageListener(redisTemplate.getKeySerializer(), redisTemplate.getValueSerializer()),
                new PatternTopic("test:channel"));
        return container;
    }


    @Slf4j
    @AllArgsConstructor
    static class RedisMessageListener implements MessageListener {

        private final RedisSerializer<?> kerSerializer;

        private final RedisSerializer<?> valueSerializer;

        @Override
        public void onMessage(Message message, byte[] pattern) {
            log.info("onMessage: key = {},  body = {}",
                    kerSerializer.deserialize(pattern),
                    valueSerializer.deserialize(message.getBody()));
        }
    }


}
