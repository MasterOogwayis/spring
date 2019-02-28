package com.zsw.conf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.SpringBootWebSecurityConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.CollectionUtils;

/**
 * @author ZhangShaowei on 2017/9/21 14:43
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     *  注意若配置了 security.ignored 系统自带静态资源忽略将失效
     * @see SpringBootWebSecurityConfiguration#DEFAULT_IGNORED
     *  spring boot 1.5.x 以上 开启了 @EnableWebSecurity 实际上 security.ignored 是被忽略了的
     *  所以在此处手动加入
     */
    @Autowired
    private SecurityProperties security;

    /**
     *
     */
    @Autowired
    private UserDetailsService myUserDetailsService;

    /**
     * @param auth AuthenticationManagerBuilder
     * @throws Exception
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
//        auth.eraseCredentials(false);
    }

    /**
     * 密码加密
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /**
     * @param http HttpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/login").defaultSuccessUrl("/").permitAll()
                .and()
                .logout().deleteCookies("remove").invalidateHttpSession(false).logoutSuccessUrl("/")
                .and()
                .csrf().disable()
                .httpBasic();
        // 自定义验证码 拦截器
        http.addFilterBefore(new LoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    /**
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 忽略资源
     * 忽略 验证码接口
     * 忽略 css、img等静态
     * 忽略 prometheus 监控接口
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/verify/code");
        // spring boot 1.5.x 以上 开启了 @EnableWebSecurity 实际上 security.ignored 是被忽略了的
        // 所以在此处手动加入
        if (!CollectionUtils.isEmpty(this.security.getIgnored())) {
            web.ignoring().antMatchers(this.security.getIgnored().toArray(new String[]{}));
        }
    }

}
