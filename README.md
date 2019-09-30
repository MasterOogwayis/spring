# spring boot & spring cloud

* spring boot 2.0           - jdk 9
* spring boot 2.0.1.RELEASE - jdk 10
* spring boot 2.1.0.M2      - jdk 11
* spring boot 2.2           - jdk 12

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
### hystrix: 

spring-cloud-starter-hystrix -> spring-cloud-starter-netflix-hystrix


## 4. datasource
### Hikari 
弃用 Druid，删除所有druid相关配置，
使用 Hikari 连接池，并最小化配置

### base

GenerationType.AUTO -> GenerationType.IDENTITY

## 5. register - eureka
    spring-cloud-starter-eureka 变更 server client 模式
    spring-cloud-starter-netflix-eureka-client
    spring-cloud-starter-netflix-eureka-server

## 6. gateway
    spring-cloud-starter--zuul
     变更
    spring-cloud-starter-netflix-zuul

## 7 config server

## 8. security & oauth2 server

<pre>
1. 2.X 不管使用哪种 PasswordEncoder ，数据库若是明文也无法验证了。所以必须加密，
   不推荐 MD5 , 推荐 BCrypt ,为兼容1.x 的 MD5 使用 MessageDigestPasswordEncoder
   默认： PasswordEncoderFactories.createDelegatingPasswordEncoder()

2. 2.x 以后通过 security.ignored 配置忽略权限无效，只能通过 继承 WebSecurityConfigurerAdapter 来实现。
   自定义 spring.security.ignored 忽略，详见：com.anze.base.config.SecurityProperties
</pre>

## 9. micrometer
    看看是否需要关闭 prometheus 的权限

## 10. admin-eureka
###  由于 第三方包的更新速度跟不上主分支，所以不再这里维护版本。自行维护！
2.0集成了UI，登录等
采集路径变更，  /actuator/**


## 11. 文件上传
    spring:
    # 以前是http
      servlet:
        multipart:
          max-file-size: 10MB
          max-request-size: 50MB
