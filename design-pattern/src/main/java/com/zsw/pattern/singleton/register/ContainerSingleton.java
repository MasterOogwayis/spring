package com.zsw.pattern.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器式写法适用于创建实例非常多的情况，便于管理。但是，是非线程安全的
 *
 * @author ZhangShaowei on 2019/3/19 16:08
 **/
public class ContainerSingleton {

    private static final Map<String, Object> ioc = new ConcurrentHashMap<>();


    public static Object getBean(String bean) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        synchronized (ioc) {
            if (!ioc.containsKey(bean)) {
                Object instance = Class.forName(bean).newInstance();
                ioc.put(bean, instance);
            }
            return ioc.get(bean);
        }

    }


}
