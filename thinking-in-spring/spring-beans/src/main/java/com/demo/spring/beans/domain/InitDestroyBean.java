package com.demo.spring.beans.domain;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 测试 bean 的初始化和销毁
 *
 * 执行顺序：java api -> spring api -> 自定义 api
 *
 * 初始化先后顺序  PostConstruct -> initMethod -> afterPropertiesSet
 * 销毁先后顺序    PreDestroy -> DisposableBean -> destroyMethod
 *
 * @author ZhangShaowei on 2021/4/27 13:58
 */
public class InitDestroyBean implements InitializingBean, DisposableBean, AutoCloseable {

    public void init2() {
        System.out.println("init method ...");
    }

    @PostConstruct
    public void init3() {
        System.out.println("PostConstruct init ...");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean afterPropertiesSet ...");
    }


    @PreDestroy
    public void destroy1() {
        System.out.println("@PreDestroy destroy ...");
    }

    public void destroy2() {
        System.out.println("destroy method ...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean destroy ...");
    }



    @Override
    public void finalize(){
        System.err.println("快死了快死了 ...");
    }

    @Override
    public void close() throws Exception {
        System.out.println("AutoCloseable close ...");
    }
}
