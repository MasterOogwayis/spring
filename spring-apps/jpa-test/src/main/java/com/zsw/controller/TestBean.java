package com.zsw.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author ZhangShaowei on 2019/11/15 13:53
 **/
@Slf4j
public class TestBean implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.debug("ApplicationContext: {}", applicationContext);
    }
}
