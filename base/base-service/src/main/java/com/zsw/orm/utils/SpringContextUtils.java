package com.zsw.orm.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * @author ZhangShaowei on 2017/9/26 9:52
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {


    /**
     *
     */
    private static ApplicationContext applicationContext;


    /**
     * Set the ApplicationContext that this object runs in.
     * Normally this call will be used to initialize the object.
     * <p>Invoked after population of normal bean properties but before an init callback such
     * as {@link InitializingBean#afterPropertiesSet()}
     * or a custom init-method. Invoked after {@link org.springframework.context.ResourceLoaderAware#setResourceLoader},
     * {@link org.springframework.context.ApplicationEventPublisherAware#setApplicationEventPublisher} and
     * {@link org.springframework.context.MessageSourceAware}, if applicable.
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws org.springframework.context.ApplicationContextException in case of context initialization errors
     * @throws BeansException              if thrown by application context methods
     * @see BeanInitializationException
     */
    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {

        if (Objects.nonNull(applicationContext)) {
            SpringContextUtils.applicationContext = applicationContext;
        }

    }

    /**
     *  获取应用上下文
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取bean
     *
     * @param name beanname
     * @param clazz interface
     * @param <T> Class
     * @return T
     */
    public static <T> T getBean(final String name, final Class<T> clazz) {
        return Optional.ofNullable(applicationContext).map(context -> context.getBean(name, clazz)).orElse(null);
    }


    /**
     * 描述：获取Bean
     *
     * @param name Bean名字
     * @return Bean实例
     */
    public static Object getBean(final String name) {
        return Optional.ofNullable(applicationContext).map(context -> context.getBean(name)).orElse(null);
    }


}
