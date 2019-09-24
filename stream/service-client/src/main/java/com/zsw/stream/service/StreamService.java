package com.zsw.stream.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2019/9/24 12:33
 **/
@Slf4j
//@Component
public class StreamService {

//    @StreamListener(Processor.OUTPUT)
    public void handle(Object person) {
        log.info("handle message: {}", person);
    }


}
