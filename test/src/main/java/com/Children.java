package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

/**
 * @author ZhangShaowei on 2019/4/16 13:35
 **/
@Slf4j
public class Children extends Parent<String> implements IParent {

    private String name = "123";

    @Override
    @EventListener
    public void sayHello() {
        log.info("Hello World!");
    }
}
