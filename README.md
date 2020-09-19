# 2020-5-7 #
##  AutoConfigurationPackages ##


# Spring Cloud #
## netflix ##
```text
Spring Cloud 服务注册、发现、负载均衡使用 3 个线程

Eureka CacheRefreshThread
Eureka InstanceInfoReplicator
Ribbon PollingServerListUpdater


Dubbo 使用的监听器，几乎能立即感知到服务上下线

思考 Zookeeper Watch 机制，对 cloud 服务进行优化？

ps：听说最近 Spring Cloud Alibaba 对 openfeign 和 Dubbo 做了兼容，采用降级调用 openfeign

```


## open feign ##
FeignAutoConfiguration
FeignClientsConfiguration
FeignRibbonClientAutoConfiguration

# Spring Boot 2.2.0.RELEASE 已发布 #
## 准备升级到 2.2.0.RELEASE & Spring Cloud Hoxton RC1 ##
### 2019-10-28 10:07:16 ###

# spring boot & spring cloud

* spring boot 2.0           - jdk 9
* spring boot 2.0.1.RELEASE - jdk 10
* spring boot 2.1.0.M2      - jdk 11
* spring boot 2.2           - jdk 13

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

连接池 切换到 lettuce，后续考虑使用 Redisson

## 3. Feign & Hystrix
### feign: 
spring-cloud-starter-feign -> spring-cloud-starter-openfeign

同一个应用2个客户端会报错 spring.main.allow-bean-definition-overriding: true
PS: 最近的版本若不配置并不会出错


### hystrix: 

spring-cloud-starter-hystrix -> spring-cloud-starter-netflix-hystrix


## 4. datasource
### Hikari 
弃用 Druid，删除所有druid相关配置，
使用 Hikari 连接池，并最小化配置

### base

GenerationType.AUTO -> GenerationType.IDENTITY

## 5. register - eureka
```text
spring-cloud-starter-eureka 变更 server client 模式
spring-cloud-starter-netflix-eureka-client
spring-cloud-starter-netflix-eureka-server
```


## 6. gateway
```text
spring-cloud-starter--zuul
 变更
spring-cloud-starter-netflix-zuul
```

## 7 config server

## 8. security & oauth2 server

```text

1. 2.X 不管使用哪种 PasswordEncoder ，数据库若是明文也无法验证了。所以必须加密，
   不推荐 MD5 , 推荐 BCrypt ,为兼容1.x 的 MD5 使用 MessageDigestPasswordEncoder
   默认： PasswordEncoderFactories.createDelegatingPasswordEncoder(),PasswordEncoderConfig

2. 2.x 以后通过 security.ignored 配置忽略权限无效，只能通过 继承 WebSecurityConfigurerAdapter 来实现。
   自定义 spring.security.ignored 忽略，详见：com.zsw.base.config.SecurityProperties
```

## 9. micrometer
### 看看是否需要关闭 prometheus 的权限

## 10. admin-eureka
###  由于 第三方包的更新速度跟不上主分支，所以不再这里维护版本。自行维护！
2.0集成了UI，登录等
采集路径变更，  /actuator/**


## 11. 文件上传
```text
spring:
# 以前是http
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 50MB
```
