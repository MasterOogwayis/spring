package com.zsw.proxy;

import com.zsw.annotation.Wrapped;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author ZhangShaowei on 2021/9/23 13:37
 */
//@Component
public class WrappedBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Wrapped.class)) {
            return new JdkProxy().getInstance(bean);
        }
        return bean;
    }
}
