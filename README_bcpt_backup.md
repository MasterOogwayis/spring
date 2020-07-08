# ab 4.0.0 - sb 2.1.9.RELEASE 升级 #
## Spring Cloud Greenwich.SR3 ##


## 1. 结构相关 && 类名变更 ##
### 1.1  关于框架 artifactId 改变的不在此特殊说明
详见 ab MarkDown.

* 加入辅助包以便于配置变更，且只在升级时使用。sb 2.x 由于包名改变很多配置已经不生效，加入下面的依赖可以兼容1.x,并在控制带打印配置变更信息以便于修改

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-properties-migrator</artifactId>
      <scope>runtime</scope>
  </dependency>
  ```

  

* ab 依赖管理使用以下方式，不需要挨个引

  ```xml
  <!-- anze base -->
  <dependency>
      <groupId>com.anze.base.microservice</groupId>
      <artifactId>anze-base-dependencies</artifactId>
      <version>${com.anze.base.version}</version>
      <type>pom</type>
      <scope>import</scope>
  </dependency>
  ```

  

### 1.2 项目结构问题

 * 包名根路径使用 com.zsw
 * client 和 api 完全隔离，即 api不直接 or 间接引用client，由于sb 2.x采用了严格模式，默认不允许存在多个 client 实现，所以 api 需要和 client 完全隔离  
 * 目前看来 client 和 api 只有 pojo 能共用，但最终都会隔离
 * pojo 每个中心完全隔离，若使用 client 自然能引用到对应的 pojo，不在各种 base-commons-xx共享某个对象，对于 Cache Key 的配置不在此限制中
 * 注意修改了 pojo package 可能会对 redis 造成影响，所以需要清空对应 key 。
 * 缓存必须设置有效期。
 * xx-commons, 通用 module 不能有太多第三方包，自己需要自己引用。仅放置一些公共配置，例如日志配置等。
 * 关于包的引入，工具类 比如 lombok , mapstruct 等可以放到公共 module，但是 例如 redis 这种提供功能的就没必要放在公共地方，在需要的地方酌情自己导入

### 1.3 其他公共继承等
* 包括不限于 JsonInclude 等公共注解不需要统一定义，根据需求自行定义，不在这些细节上做限制  
  例如取消了 BaseDto
* 所有对象模型都必须实现 Serializable

### 1.4 类名变更
  * ResultData -> ApiResponse，尽量使用内部提供的静态方法
  * 所有 api 必须使用同一种返回数据格式，以便统一数据返回格式
  * 统一异常处理



### 1.5 异常 && 返回码
* 不要直接抛出 Exception，多使用自定义异常以便处理信息，携带异常码，方便快速定位错误。

* 不要在 controller 里面抛出异常，这个不用说，在mvc的年代就这样，controller抛出异常给虚拟机处理么？

* 统一异常处理，所有异常均在 controller 层 or 他的父类捕获，然后统一格式返回（统一异常处理，统一返回类型）

  

### 1.6 启动类

* 弃用 ab 包含的 BaseApplication， ab 4.x 以上只关注了com.anze 下面的组件，详见ab README.md

* 项目中在 com.zsw 包下使用自己的 启动类，例如 TcApiApp， PcApiApp等，同步修改maven 配置的 启动类mainClass

```xml
<plugin>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-maven-plugin</artifactId>
  <configuration>
      <mainClass>com.zsw.TcApiApp</mainClass>
  </configuration>
  <executions>
      <execution>
          <goals>
              <goal>repackage</goal>
          </goals>
      </execution>
  </executions>
</plugin>
```

### 1.7 结构说明
~~~
platform-soa
  - base
     - bcpt-baidu-support 存放于百度地图相关api和工具，若不使用不需要引
     - bcpt-base-cache    需要使用缓存就引入，存放缓存配置
     - bcpt-base-commons  所有业务相关全部移除，只存放静态类、常量、枚举、各种utils
     - base-base-http     http工具全部放这里
     - bcpt-base-logging  旧包，日志相关配置
     - bcpt-mapstruct     对象转换
     - bcpt-base-pojo     数据模型，目前还存放一些无法拆除的 dto
     - base-base-wechat   与微信相关的放到这里，以后会区分公众号和小程序
     - base-mq-commons    mq
  - oc
     - bcpt-oc-pojo       存放所有使用到的对象模型，只要是自己业务的切在client里面使用到的理论上都放到这里
     - bcpt-oc-client     接口，如果在这里面自己加入了缓存，请使用 spring注解模式，若使用到了常量可以引入bcpt-base-commons。不要再这里面处理业务
     - bcpt-oc-persistence
     - bcpt-oc-service 
     - bcpt-oc-api        已经和client完全隔离，不需要去实现client
     - bcpt-oc-controller
  ....
~~~



## 2. Redis ##
* 1. 连接池 会用 lettuce 替换 jedis，详见配置分支 upgrade。(jedis -> lettuce -> Redisson))。
* 2. 修改 key 配置方式，详见 CacheConsts 及其 子类
* 3. 多使用 spring 的缓存注解 @Cacheable @CacheEvit @CachePut @Caching 等
* 4. 注意这个版本的 @Cacheable 默认不会忽略 null， 需要加上属性 unless="#result == null"
* 5. redis 连接配置

```yaml
spring:
  redis:
    host: 192.168.1.12
    port: 6379
    password: Anf34l_Fs
    database: 0
    lettuce:
      pool:
        max-active: 8
#        max-wait: -1
        max-idle: 8
#        min-idle: 0
    timeout: 60
```



## 3. 线程池 ##
### 可以使用自带的线程池配置 ###
 * 1.使用自动配置的 ThreadPoolTaskScheduler or ThreadPoolTaskExceutor
 * 2.根据需求调整参数，配置或代码方式
 * 3.同一个实例创建过多的线程池通常是无意义的
 * 4.详见：ThreadPoolConfigure
```yaml
详见配置文件： ThreadPoolConfiguration

spring:
  task:
    execution:
      pool:
        max-size: 100
        queue-capacity: 500
        ...
```


## 4 JPA ##
### 4.1 数据库连接池使用 Hikari CP ###
弃用 druid，使用 Hikari CP 连接池，sb 2.0 默认应入的连接池，其他配置保持最小化  
注意：由于升级了 mysql-connector 8.x  url 需要带上时区&serverTimezone=Asia/Shanghai

```yaml
spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    url: jdbc:mysql://192.168.1.12:3306/bcpt_soa_fc_test1?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: root
    password: root
    driverClassName: com.mysql.cj.jdbc.Driver
    hikari:
      minimum-idle: 5
      maximum-pool-size: 50
#      max-lifetime: 30000
      idle-timeout: 600000
      connection-timeout: 30000
      register-mbeans: true
  #JPA Configuration:
  jpa:
    database: MYSQL
    show-sql: true
    generate-ddl: true
    hibernate:
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
      ddl-auto: validate
    database-platform: org.hibernate.dialect.MySQLDialect
```
### 4.2 base
  Entity @Id 主键配置  GenerationType.AUTO -> GenerationType.IDENTITY

### 4.3 注解
  关联层次多千万不要使用 @NotFound(action = NotFoundAction.IGNORE)  
  会导致懒加载失败，递归加载所有数据

### 4.3 持久层 Repository 实现
详见 com.anze.base.persistence.repository.BaseRepository

### 4.4 jdbc
jdbc 5.x  -> 8.x

### 4.5 返回类型
使用 Tuple 代替 Map<String, Object>


## 5. DiscoveryClient ##
### 客户端问题
FeignClientsRegistrar#line 243，新版直接使用了 contextId + FeignClient 作为alias
如果一个应用有多个client，就会无法启动。这个 commit 看不懂... 大多评论说这 author 有病 ...
所以需要开启  
```yaml
spring:
  main:
    allow-bean-definition-overriding: true
```
注意：只能配置在 application.yml or bootstrap.yml 里面，无法通过配置中心生效


## 6. 文件上传 ##
### 配置变更 ###
```yaml
spring:
# 以前是http
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 50MB
```

## 7. 鉴权 security & oauth2

* 2.x 无论使用哪种 PasswordEncoder ，数据库若是明文也无法验证了。所以必须加密，不推荐 MD5 , 推荐 BCrypt ,为兼容1.x 的 MD5 使用 MessageDigestPasswordEncoder
  默认： PasswordEncoderFactories.createDelegatingPasswordEncoder()
  详见： com.anze.base.oauth2.config.WebSecurityConfig

* 2.x 以后通过 security.ignored 配置忽略权限无效，只能通过 继承 WebSecurityConfigurerAdapter 来实现。
  使用自定义 spring.security.ignored 忽略，详见：com.anze.base.config.SecurityProperties




## 8. swagger2  ##
### 兼容问题  ###
 * 由于 springfox-swagger 还处于 sb 2.x 的快照版本？？？，并没有发布正式版。故需要做以下调整
 * 需要使用强引用：spring-plugin-core 1.2.0.RELEASE，和 sb 2.x 可能有冲突
 * 开发测试阶段只需要 swagger2-annotation。在需要生成 api 的地方才使用 swagger-ui or api。
 * monitor-xx.yml 中剔除了 swagger2.enabled=true，使用到时自行开启

## 9. 其他应用配置改动 ##

* 1.对所有 api 使用的配置都需要修改，数据库连接池。见 4
* 2.所有启动配置需要增加属性
* 3.选择改动 - 线程池配置 or 参考 ThreadPoolConfigure
2. bcpt-xx-api-xx.yml

```yaml
spring:
  task:
    execution:
      pool:
        max-size: 100
        queue-capacity: 500
```



## 10. 其他
  * 弃用 各种工具包下面的BeanUtils，使用 MapStruct 进行对象转换，更新。BeanUtils 不是很灵活，再不济使用 JavassistBeanUtils
  * RocketMq 全部切换到新版，MQ消息体是带类型的，不要把json和非json消息发到同一个topic 不同业务理应分离
  * 使用 java.time 包下面的工具处理时间，不推荐 SimpleDateFormat。参考 DateTimeUtils




## 11. 缓存类型变更
  * 1.考虑到不是全量升级，为兼容低版本，使用json str 缓存，所有这类地方标记 FIXME





## 20. 关于升级顺序
   * 1.解决编译错误，主要是类名变更
   * 2.非业务中心优先升级，如 eureka，config-server，zuul -> gateway
   * 3.与业务相关的待定
   * 4.升级时按顺序确定 1-10 项说明


