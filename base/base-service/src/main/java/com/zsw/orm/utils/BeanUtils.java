package com.zsw.orm.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhangShaowei on 2019/7/15 16:53
 **/
public class BeanUtils {

    public static final Map<Class, Map<String, PropertyDescriptor>> CACHE = new ConcurrentHashMap<>(32);

    public static Map<String, PropertyDescriptor> getSynonymPropertiesMap(Class cls) {
        return CACHE.computeIfAbsent(cls, clazz -> {
            Map<String, PropertyDescriptor> propertiesMap = new HashMap<>();
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor property : propertyDescriptors) {
                    if (property.getReadMethod() != null && property.getWriteMethod() != null) {
                        propertiesMap.put(property.getName().replace("_", "").toUpperCase(), property);
                    }
                }
            } catch (IntrospectionException e) {
                throw new RuntimeException(e);
            }
            return propertiesMap;
        });
    }

    /**
     * 相同含义字段名属性复制，即忽略字段名中的大小写和下划线，但字段类型必须兼容，不支持基本类型和包装类型的属性之间复制。
     */
    public static <T> T copyProperties(Object source, T target) throws Exception {
        if (source == null || target == null) return target;
        Map<String, PropertyDescriptor> sourcePropertyMap = getSynonymPropertiesMap(source.getClass());
        Map<String, PropertyDescriptor> targetPropertyMap = getSynonymPropertiesMap(target.getClass());
        PropertyDescriptor[] targetPds = targetPropertyMap.values().toArray(new PropertyDescriptor[]{});
        for (PropertyDescriptor targetPd : targetPds) {
            PropertyDescriptor sourceProperty = sourcePropertyMap.get(targetPd.getName().toUpperCase());
            if (sourceProperty != null && sourceProperty.getPropertyType().isAssignableFrom(targetPd.getPropertyType())) {
                targetPd.getWriteMethod().invoke(target, sourceProperty.getReadMethod().invoke(source));
            }
        }
        return target;
    }


}
