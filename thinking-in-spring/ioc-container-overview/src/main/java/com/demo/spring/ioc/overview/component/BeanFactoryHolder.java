package com.demo.spring.ioc.overview.component;

import lombok.Getter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2021/4/25 9:30
 */
@Getter
@Component
public class BeanFactoryHolder {

    @Autowired
    private BeanFactory beanFactory;

}
