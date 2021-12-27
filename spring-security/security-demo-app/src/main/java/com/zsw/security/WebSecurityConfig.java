package com.zsw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author ZhangShaowei on 2021/12/21 15:22
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration(proxyBeanMethods = false)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/hello");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 详细的角色资源权限控制，主要是动态权限
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
//                        object.setAccessDecisionManager(accessDecisionManager);
//                        object.setSecurityMetadataSource(securityMetadataSource);
//                        return object;
//                    }
//                })
                .antMatchers("/sum").hasRole("admin")
                .anyRequest().authenticated()
                .and().formLogin()
                .and().httpBasic()
                // 第二次登录会导致第一次登录失效，防止多端重复登录
//                .and().sessionManagement(session -> session.maximumSessions(1).maxSessionsPreventsLogin(true))
                // 不跳转登录 直接显示403
//                .and().exceptionHandling()
//                .defaultAuthenticationEntryPointFor(new Http403ForbiddenEntryPoint(), new AntPathRequestMatcher("/**"))
                .and().csrf().disable()
                .anonymous().disable();
    }
}
