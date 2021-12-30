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