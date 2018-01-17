package com.boot;

import com.boot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ZhangShaowei on 2017/4/24 15:41
 */
//@Configuration
//@EnableWebSecurity
public class SecurityConfig { //extends WebSecurityConfigurerAdapter {


    /**
     *
     */
    @Autowired
    private UserService userService;

    /**
     * @param http
     * @throws Exception
     */
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/")
//                .access("hasRole('READER')")
//                .antMatchers("/**")
//                .permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error=true");
//    }

    /**
     * @param auth
     * @throws Exception
     */
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(this.userService);
//    }


}
