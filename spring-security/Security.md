# Spring Security #

```text

spring.factories -> SecurityAutoConfiguration -> WebSecurityEnablerConfiguration 
-> @EnableWebSecurity -> WebSecurityConfiguration -> springSecurityFilterChain 
-> SecurityFilterChain -> DelegatingFilterProxyRegistrationBean -> (IgnoredAntMatcher, Filter)FilterChainProxy
```
