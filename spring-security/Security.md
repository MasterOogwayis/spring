# Spring Security #

```text

spring.factories -> SecurityAutoConfiguration -> WebSecurityEnablerConfiguration 
-> @EnableWebSecurity -> WebSecurityConfiguration -> springSecurityFilterChain 
-> SecurityFilterChain -> (IgnoredAntMatcher, Filter)FilterChainProxy
```
