package com.zsw.base.oauth2.annotation;

import com.zsw.base.oauth2.config.AuthorizationServerMakerConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ZhangShaowei
 */
@EnableAuthorizationServer
@Import(AuthorizationServerMakerConfiguration.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableZswAuthorizationServer {

    /**
     * 认证后是否缓存用户信息，用于 resource 端解析Thread
     *
     * @return boolean
     */
    boolean cachingUserInfo() default true;

    /**
     * 缓存用户信息的 namespace
     * default -> namespace:clientId:username
     *
     * @return namespace
     */
    String cachingNamespace() default "security:oauth2:userInfo";

}
