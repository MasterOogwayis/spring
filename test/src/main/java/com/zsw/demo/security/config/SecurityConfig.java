package com.zsw.demo.security.config;

/**
 * @author ZhangShaowei on 2019/9/29 14:23
 **/
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//
////    @Override
////    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .passwordEncoder(new MessageDigestPasswordEncoder("MD5"))
////                .withUser("admin").password("96e79218965eb72c92a549dd5a330112").authorities("ADMIN");
////        auth.eraseCredentials(false);
////    }
//
//    /**
//     * 由于加载顺序问题，{@link org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration}可能会先加载
//     *
//     * @return userDetailServer
//     */
//    @Override
//    @Primary
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("ADMIN", "USER")
//                        .build()
//        );
//        return manager;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                .anyRequest().authenticated()
//                .and().formLogin()
//                .and().httpBasic()
//                .and()
//                .csrf().disable();
//    }
//}
