package com;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author ZhangShaowei on 2019/9/24 13:23
 **/
public interface CustomSource {

    /**
     * Input channel name.
     */
    String INPUT = Source.OUTPUT;

    /**
     * @return input channel.
     */
    @Input(INPUT)
    SubscribableChannel input();

}
