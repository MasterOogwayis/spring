package com.zsw.mq;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * use rocket to send message.
 *
 * @author ZhangShaowei on 2018/3/12 11:19
 **/
@Service
public class RocketMqService implements BaseMqService {
    
    /**
     * logger
     */
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    
    @Override
    public void sendSimpleMessage(BaseMqMessage message) {
        this.logger.info("rocketmq send message: " + message.toString());
    }
}
