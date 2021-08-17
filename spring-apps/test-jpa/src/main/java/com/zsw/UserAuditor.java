package com.zsw;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author ZhangShaowei on 2020/9/25 14:15
 */
@Configuration(proxyBeanMethods = false)
public class UserAuditor implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("sys");
    }
}
