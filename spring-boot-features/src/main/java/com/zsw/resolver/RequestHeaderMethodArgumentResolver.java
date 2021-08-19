package com.zsw.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author ZhangShaowei on 2021/8/19 10:49
 */
@AllArgsConstructor
public class RequestHeaderMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final ObjectMapper mapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestHeaderObject.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Iterable<String> iterable = webRequest::getHeaderNames;
        Map<String, String> values = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toMap(Function.identity(), webRequest::getHeader));
        if (parameter.getParameterType().isAssignableFrom(Map.class)) {
            return values;
        }
        return this.mapper.readValue(this.mapper.writeValueAsString(values), parameter.getParameterType());
    }


//    private static final ConcurrentHashMap<Class<?>, Map<String, Method>> WRITER = new ConcurrentHashMap<>();


//    @Override
//    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//        Map<String, String> result = new LinkedHashMap<>();
//        Object headerObj = BeanUtils.instantiateClass(parameter.getParameterType());
//        getWriter(parameter.getParameterType())
//                .forEach((n, m) -> {
//                    invokeWriteMethod(headerObj, m, webRequest.getHeader(n));
//                });
//        return headerObj;
//    }
//
//    @SneakyThrows
//    private void invokeWriteMethod(Object target, Method method, String value) {
//        method.invoke(target, value);
//    }
//
//    private Map<String, Method> getWriter(Class<?> clazz) throws Exception {
//        return WRITER.computeIfAbsent(clazz, cla -> {
//            try {
//                BeanInfo beanInfo = Introspector.getBeanInfo(cla);
//                HashMap<String, Method> writeMethods = new HashMap<>();
//                for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
//                    if ("class".equals(propertyDescriptor.getDisplayName())) {
//                        continue;
//                    }
//                    writeMethods.put(propertyDescriptor.getDisplayName(), propertyDescriptor.getWriteMethod());
//                }
//                return writeMethods;
//            } catch (IntrospectionException e) {
//                e.printStackTrace();
//            }
//            return Collections.emptyMap();
//        });
//    }
}
