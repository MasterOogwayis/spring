package com.zsw.api;

import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhangShaowei on 2021/7/2 17:05
 */
public class RequestHeaderCustomMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final ConcurrentHashMap<Class<?>, Map<String, Method>> WRITER = new ConcurrentHashMap<>();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return (parameter.hasParameterAnnotation(RequestHeaderObject.class) &&
                HeaderInjection.class.isAssignableFrom(parameter.getParameterType()));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Map<String, String> result = new LinkedHashMap<>();
        Object headerObj = BeanUtils.instantiateClass(parameter.getParameterType());
        getWriter(parameter.getParameterType())
                .forEach((n, m) -> {
                    invokeWriteMethod(headerObj, m, webRequest.getHeader(n));
                });
        return headerObj;
    }

    @SneakyThrows
    private void invokeWriteMethod(Object target, Method method, String value) {
        method.invoke(target, value);
    }

    private Map<String, Method> getWriter(Class<?> clazz) throws Exception {
        return WRITER.computeIfAbsent(clazz, cla -> {
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(cla);
                HashMap<String, Method> writeMethods = new HashMap<>();
                for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                    if ("class".equals(propertyDescriptor.getDisplayName())) {
                        continue;
                    }
                    writeMethods.put(propertyDescriptor.getDisplayName(), propertyDescriptor.getWriteMethod());
                }
                return writeMethods;
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
            return Collections.emptyMap();
        });
    }
}
