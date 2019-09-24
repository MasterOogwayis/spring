package com.zsw.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;

/**
 * @author ZhangShaowei on 2019/9/23 14:46
 **/
public interface CustomSource {

    String OUTPUT = Sink.INPUT;

    @Output(OUTPUT)
    MessageChannel output();

}
