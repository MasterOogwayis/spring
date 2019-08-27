package com.zsw.rabbit.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RabbitType {

    /**
     *
     */
    DIRECT("direct", ""),
    FANOUT("fanout", ""),
    HEADERS("headers", ""),
    TOPIC("topic", ""),

    /**
     * 插件提供的延迟队列
     */
    DELAYED("x-delayed-message", "");


    private String value;

    private String desc;


}
