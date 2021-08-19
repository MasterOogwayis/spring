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
}
