# Spring Security #

```text

spring.factories -> SecurityAutoConfiguration -> WebSecurityEnablerConfiguration 
-> @EnableWebSecurity -> WebSecurityConfiguration -> springSecurityFilterChain 
-> SecurityFilterChain -> DelegatingFilterProxyRegistrationBean -> (IgnoredAntMatcher, Filter)FilterChainProxy
```



## 调用链 ##
```text
ApplicationFilterChain -> DelegatingFilterProxyRegistrationBean -> FilterChainProxy -> List<SecurityFilterChain>.matches(request).getFilters()
```




## 并发 ##
```text
多线程下 {@link SecurityContextHolder#getContext()} 会获取失败
有一下 3 种方式解决
 * 1. 使用 security executor {@link DelegatingSecurityContextExecutor} {@link DelegatingSecurityContextExecutorService}
 * 2. 使用 security runnable {@link DelegatingSecurityContextRunnable}
 * 3. 通过系统参数设置 SecurityContextHolder {@link SecurityContextHolder#SYSTEM_PROPERTY} 策略为 {@link SecurityContextHolder#MODE_INHERITABLETHREADLOCAL}
 * 4. 其他所有并发类 DelegatingSecurityContext***
```


