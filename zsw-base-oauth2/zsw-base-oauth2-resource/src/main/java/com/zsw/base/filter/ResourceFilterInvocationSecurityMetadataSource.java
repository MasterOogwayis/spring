package com.zsw.base.filter;

import com.anze.base.persistence.repository.MenuRepository;
import com.anze.base.pojo.dto.role.SourceDto;
import com.anze.base.security.listener.PermissionsChangedEvent;
import com.anze.base.security.listener.PermissionsChangedListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.persistence.Tuple;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.anze.base.utils.MethodParser.parser;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * 需要重载权限
 * 1. 角色授权，启用停用
 * 2. 资源编辑，删除，启用，停用
 * 特别注意由于使用了 PathMatcher 匹配资源，所以无法单独更新某个资源的权限
 * 所以每次权限变动都需要全量更新
 *
 * @author ZhangShaowei on 2021/5/12 13:24
 */
@Slf4j
@Transactional(readOnly = true)
@Component
public class ResourceFilterInvocationSecurityMetadataSource
        implements FilterInvocationSecurityMetadataSource, InitializingBean, PermissionsChangedListener {

    public final PathMatcher pathMatcher = new AntPathMatcher();

    /**
     *
     */
    @Autowired
    private MenuRepository menuRepository;

    /**
     * FIXME 使用上下文缓存以便激活分布式权限管理
     * <p>
     * method -> url resources -> role
     */
    private static final Map<String, Map<String, List<String>>> RESOURCE_PERMISSIONS = new ConcurrentHashMap<>();

    /**
     * resource -> authority roles
     *
     * @param object resource
     * @return ConfigAttribute
     * @throws IllegalArgumentException e
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        String method = ((FilterInvocation) object).getHttpRequest().getMethod();
        return RESOURCE_PERMISSIONS.getOrDefault(method, Collections.emptyMap())
                .entrySet()
                .stream()
                .filter(entity -> pathMatcher.match(entity.getKey(), requestUrl))
                .map(Map.Entry::getValue)
                .flatMap(List::stream)
                .distinct()
                .map(SecurityConfig::new)
                .collect(toList());
    }

    /**
     * @return 所有资源权限
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * 预加载权限
     */
    @Override
    public void afterPropertiesSet() {
        this.reload();
    }


    /**
     * 刷新权限
     */
    public synchronized void reload() {
        long timer = System.currentTimeMillis();
        log.debug("authority reload begin ...");
        List<Tuple> tuples = menuRepository.loadRolesAndSources();
        // method -> url -> roles
        Map<String, Map<String, List<String>>> refreshAble = tuples
                .stream()
                .map(tuple -> SourceDto.builder()
                        .method(parser(tuple.get("method", Number.class).intValue()))
                        .uri(tuple.get("targetUrl", String.class))
                        .role(tuple.get("mark", String.class))
                        .build()
                )
                .collect(
                        groupingBy(
                                SourceDto::getMethod,
                                groupingBy(
                                        SourceDto::getUri,
                                        mapping(SourceDto::getRole, toList())
                                ))
                );
        RESOURCE_PERMISSIONS.clear();
        RESOURCE_PERMISSIONS.putAll(refreshAble);
        log.debug("authority reload success, {}ms", System.currentTimeMillis() - timer);
    }

    /**
     * @param event PermissionsChangedEvent
     */
    @Override
    public void onApplicationEvent(PermissionsChangedEvent event) {
        this.reload();
    }
}
