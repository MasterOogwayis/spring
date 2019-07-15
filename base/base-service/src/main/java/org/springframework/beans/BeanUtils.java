package org.springframework.beans;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhangShaowei on 2019/7/15 16:53
 **/
public class BeanUtils {

    public static final Map<Class, Map<String, PropertyDescriptor>> CACHE = new ConcurrentHashMap<>(32);

    public static Map<String, PropertyDescriptor> getSynonymPropertiesMap(Class cls) throws IntrospectionException {
        if (CACHE.containsKey(cls)) {
            return CACHE.get(cls);
        }

        Map<String, PropertyDescriptor> propertiesMap = new LinkedHashMap<>();
        PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(cls).getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            if (property.getReadMethod() != null && property.getWriteMethod() != null) {
                propertiesMap.put(property.getName(), property);
            }
        }
        CACHE.put(cls, propertiesMap);
        return propertiesMap;
    }

    /**
     * 相同含义字段名属性复制，即忽略字段名中的大小写和下划线，但字段类型必须兼容，不支持基本类型和包装类型的属性之间复制。
     */
    public static <T> T copyProperties(Object source, T target) throws Exception {
        if (source == null || target == null) return target;
        Map<String, PropertyDescriptor> sourcePropertyMap = getSynonymPropertiesMap(source.getClass());
        Map<String, PropertyDescriptor> targetPropertyMap = getSynonymPropertiesMap(target.getClass());
        for (Map.Entry<String, PropertyDescriptor> entry : targetPropertyMap.entrySet()) {
            PropertyDescriptor sourceProperty = sourcePropertyMap.get(entry.getKey());
            if (sourceProperty != null && sourceProperty.getPropertyType().isAssignableFrom(entry.getValue().getPropertyType())) {
                entry.getValue().getWriteMethod().invoke(target, sourceProperty.getReadMethod().invoke(source));
            }
        }
        return target;
    }


}
