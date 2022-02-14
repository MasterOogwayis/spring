# 记录一些简单例子和偶尔的思绪 #



# 2021-3-12 #
## 全链路蓝绿，灰度发布 ##
<https://github.com/Nepxion/Discovery>

# 2020-12-25 #
## 升级 Spring Cloud 版本 ##
### netflix 正式完蛋 ###
```text
接下来尝试其他解决方案
```

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

# spring boot & spring cloud

* spring boot 2.0           - jdk 9
* spring boot 2.0.1.RELEASE - jdk 10
* spring boot 2.1.0.M2      - jdk 11
* spring boot 2.2           - jdk 13

## 11. 文件上传
```text
spring:
# 以前是http
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 50MB
```

AnnotationConfigUtils


spring-petclinic-jpa
JpaRepositoryFactoryBean

@EventListener -> EventListenerMethodProcessor -> ApplicationListenerMethodAdapter
ConversionService -> 注入-类型装换

## 12. Security
```text
多线程如何使用 SecurityContextHolder -> 
1. DelegatingSecurityContextExecutor
2. DelegatingSecurityContextRunnable
```


# 2022-2-14 #
