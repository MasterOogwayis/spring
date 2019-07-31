# spring boot & spring cloud

## 1.x 迁移 2.x
[官方迁移文档](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Migration-Guide)
[参数变更](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Configuration-Changelog)

## 1. Eureka
### server: 
spring-cloud-starter-eureka-server → spring-cloud-starter-netflix-eureka-server

### client: 
spring-cloud-starter-eureka-client → spring-cloud-starter-netflix-eureka-client


## 2. Redis
参数配置变更，详细参考 migrator

## 3. Feign & Hystrix
### feign: 
spring-cloud-starter-feign -> spring-cloud-starter-openfeign
### hystrix: 

spring-cloud-starter-hystrix -> spring-cloud-starter-netflix-hystrix


## 4. datasource
### Hikari 
弃用 Druid




## 4. admin-eureka
2.0集成了UI，登录等
采集路径变更，  /actuator/ + ***





## 10. 监控
### 暴露端点

<pre>
management:
  endpoints:
    web:
      exposure:
        include: "*"  
  endpoint:
    health:
      show-details: ALWAYS
</pre>


