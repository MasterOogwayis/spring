package com.zsw.audit;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 不建议在审计里面使用 jpa 访问那数据，会导致各种恶样的问题
 *
 * @author ZhangShaowei on 2020/9/25 14:15
 */
@Configuration
public class UserAuditor implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("sys");
    }
}
