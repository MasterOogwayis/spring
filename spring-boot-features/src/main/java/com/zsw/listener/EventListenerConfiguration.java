package com.zsw.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * 事件监听器，注意有一些事件发生的时候 ApplicationContext 甚至都还没有创建，所以需要使用 spi 的方式
 * <p>
 * https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.spring-application.application-events-and-listeners
 *
 * @author ZhangShaowei on 2021/8/18 14:48
 * @see MyEventListener
 */
@Slf4j
@Configuration
public class EventListenerConfiguration {

    /**
     * @param event ApplicationEvent
     */
    @EventListener
    public void listener(ApplicationEvent event) {
        log.info("event active {}", event);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void started(ApplicationStartedEvent event) {
        log.info("Application started. context = {}", event.getApplicationContext());
    }

    /**
     * 这样配置是无法接收到这个 Event，为什么？因为 Environment 准备好的时候 context 甚至还没创建
     * 所以可以使用 SPI 的方式配置这种事件监听器
     *
     * @see MyEventListener
     */
//    @EventListener(ApplicationEnvironmentPreparedEvent.class)
//    public void preparedEvent(ApplicationEnvironmentPreparedEvent event) {
//        log.info("ApplicationEnvironmentPreparedEvent: {}", event.getEnvironment());
//    }


//    @EventListener
//    public void onStateChange(AvailabilityChangeEvent<ReadinessState> event) {
//        log.info("onStateChange {}", event);
//        switch (event.getState()) {
//            case ACCEPTING_TRAFFIC:
//                // create file /tmp/healthy
//                break;
//            case REFUSING_TRAFFIC:
//                // remove file /tmp/healthy
//                break;
//            default:
//        }
//    }


}
