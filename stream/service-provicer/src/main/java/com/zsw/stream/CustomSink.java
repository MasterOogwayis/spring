package com.zsw.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.SubscribableChannel;

public interface CustomSink {
    String INPUT = Source.OUTPUT;

    @Input(INPUT)
    SubscribableChannel channel();
}
