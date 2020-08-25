package com.zsw.test.api;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2020/8/13 9:22
 */
@Component
public class SimpleService implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init");
    }
}
