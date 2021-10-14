package com.demo.spring.ioc.overview.domain;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author ZhangShaowei on 2019/6/6 14:55
 **/
public class RpcClientFactoryBean implements FactoryBean<String> {


    @Override
    public String getObject() throws Exception {
        return "Hello World!";
    }

    @Override
    public Class<?> getObjectType() {
        return String.class;
    }


    @Override
    public boolean isSingleton() {
        return true;
    }
}
