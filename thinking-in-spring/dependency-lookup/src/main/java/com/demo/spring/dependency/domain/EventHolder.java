package com.demo.spring.dependency.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2021/4/28 16:38
 */
@Slf4j
@Component
public class EventHolder {

    @EventListener
    public void onEvent(ApplicationEvent event) {
        log.info("onEvent: {}", event);
    }

}
