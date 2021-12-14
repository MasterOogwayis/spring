package com.zsw.base.oauth2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2.x 以后通过配置忽略权限无效，只能通过 继承 WebSecurityConfigurerAdapter 来实现
 *
 * @author ZhangShaowei on 2019/8/19 11:46
 **/
@ConfigurationProperties(prefix = "spring.security")
public class SecurityProperties {

    private List<String> ignored;


    /**
     *
     */
    public List<String> getIgnored() {
        return ignored;
    }

    /**
     *
     */
    public void setIgnored(List<String> ignored) {
        this.ignored = ignored;
    }


    /**
     * 排除web常规资源
     */
    private static List<String> DEFAULT_IGNORED = Arrays.asList("/css/**", "/js/**",
            "/images/**", "/webjars/**", "/**/favicon.ico");

    /**
     * 排除swagger api文档相关接口权限
     * 需要忽略的路径
     *
     * @return
     */
    public Set<String> ignored() {
        // security.ignored
        Set<String> ignored = new HashSet<>(DEFAULT_IGNORED);
        ignored.add("/*.html");
        ignored.add("/v2/**");
        ignored.add("/swagger-resources/**");
        ignored.add("/server/version");
        // 排除监控采集接口
        ignored.add("/actuator/prometheus");

        if (!CollectionUtils.isEmpty(this.ignored)) {
            ignored.addAll(this.ignored);
        }

        return ignored;
    }

}
