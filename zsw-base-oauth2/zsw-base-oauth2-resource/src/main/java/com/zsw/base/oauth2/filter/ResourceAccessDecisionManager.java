package com.zsw.base.oauth2.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 在这里验证资源权限
 *
 * @author ZhangShaowei on 2021/5/12 13:28
 */
@Slf4j
@Component
public class ResourceAccessDecisionManager implements AccessDecisionManager {
    /**
     * 资源权限验证
     *
     * @param authentication   Authentication 已认证用户
     * @param object           资源
     * @param configAttributes 访问资源需要具有的权限，这里只角色
     * @throws AccessDeniedException               403
     * @throws InsufficientAuthenticationException 400
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        Map<String, ? extends ConfigAttribute> needAuthority = configAttributes
                .stream()
                .collect(Collectors.toMap(ConfigAttribute::getAttribute, Function.identity()));
        authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(needAuthority::containsKey)
                .peek(mark -> {
                    if (log.isDebugEnabled()) {
                        String requestUrl = ((FilterInvocation) object).getRequestUrl();
                        log.debug("resource={}, authority={}", requestUrl, mark);
                    }
                })
                .findAny()
                .orElseThrow(() -> new AccessDeniedException("无权限"));
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
