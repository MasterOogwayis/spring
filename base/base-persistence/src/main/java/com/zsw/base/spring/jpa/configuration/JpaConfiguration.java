package com.zsw.base.spring.jpa.configuration;

import com.zsw.base.repository.jpa.CustomRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * durid 配置与 application.yml中 使用 Druid Spring Boot Starter
 *
 * @author ZhangShaowei on 2017/9/12 15:57
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.*", repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
@EnableTransactionManagement
@EntityScan(basePackages = "com")
public class JpaConfiguration {


//    private Logger logger = LoggerFactory.getLogger(JpaBaseConfiguration.class);
//
//    @Value("${spring.datasource.url}")
//    private String dbUrl;
//
//    @Value("${spring.datasource.username}")
//    private String username;
//
//    @Value("${spring.datasource.password}")
//    private String password;
//
//    @Value("${spring.datasource.driverClassName}")
//    private String driverClassName;
//
//    @Value("${spring.datasource.initialSize}")
//    private int initialSize;
//
//    @Value("${spring.datasource.minIdle}")
//    private int minIdle;
//
//    @Value("${spring.datasource.maxActive}")
//    private int maxActive;
//
//    @Value("${spring.datasource.maxWait}")
//    private int maxWait;
//
//    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
//    private int timeBetweenEvictionRunsMillis;
//
//    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
//    private int minEvictableIdleTimeMillis;
//
//    @Value("${spring.datasource.validationQuery}")
//    private String validationQuery;
//
//    @Value("${spring.datasource.testWhileIdle}")
//    private boolean testWhileIdle;
//
//    @Value("${spring.datasource.testOnBorrow}")
//    private boolean testOnBorrow;
//
//    @Value("${spring.datasource.testOnReturn}")
//    private boolean testOnReturn;
//
//    @Value("${spring.datasource.poolPreparedStatements}")
//    private boolean poolPreparedStatements;
//
//    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
//    private int maxPoolPreparedStatementPerConnectionSize;
//
//    @Value("${spring.datasource.filters}")
//    private String filters;
//
//    @Value("${spring.datasource.connectionProperties}")
//    private String connectionProperties;
//
//    @Value("${spring.datasource.removeAbandoned}")
//    private Boolean removeAbandoned;
//
//    @Value("${spring.datasource.removeAbandonedTimeout}")
//    private Integer removeAbandonedTimeout;
//
//    @Value("${spring.datasource.logAbandoned}")
//    private Boolean logAbandoned;
//
//    @Value("${spring.datasource.keepAlive}")
//    private Boolean keepAlive;
//
//    /**
//     * 声明其为Bean实例
//     * 在同样的DataSource中，首先使用被标注的DataSource
//     *
//     * @return
//     */
//    @Bean
//    @Primary
//    public DataSource dataSource() {
//        DruidDataSource datasource = new DruidDataSource();
//
//        datasource.setUrl(this.dbUrl);
//        datasource.setUsername(username);
//        datasource.setPassword(password);
//        datasource.setDriverClassName(driverClassName);
//
//        //configuration
//        datasource.setInitialSize(initialSize);
//        datasource.setMinIdle(minIdle);
//        datasource.setMaxActive(maxActive);
//        datasource.setMaxWait(maxWait);
//        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//        datasource.setValidationQuery(validationQuery);
//        datasource.setTestWhileIdle(testWhileIdle);
//        datasource.setTestOnBorrow(testOnBorrow);
//        datasource.setTestOnReturn(testOnReturn);
//        datasource.setPoolPreparedStatements(poolPreparedStatements);
//        datasource.setRemoveAbandoned(removeAbandoned);
//        datasource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
//        datasource.setLogAbandoned(logAbandoned);
//        datasource.setKeepAlive(keepAlive);
//
//        datasource
//                .setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
//        try {
//            datasource.setFilters(filters);
//        } catch (SQLException e) {
//            logger.error("druid configuration initialization filter", e);
//        }
//        datasource.setConnectionProperties(connectionProperties);
//
//        return datasource;
//    }
//
//    /**
//     * Druid 监控面板
//     */
//    @Configuration
////    @ConditionalOnProperty(name = "druid.monitor.enable", havingValue = "true")
//    class DruidMonitorConfiguration {
//        @Bean
//        public ServletRegistrationBean druidServlet() {
//            ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
//            //添加初始化参数：initParams
////        /** 白名单，如果不配置或value为空，则允许所有 */
////        servletRegistrationBean.addInitParameter("allow","127.0.0.1,192.0.0.1");
////        /** 黑名单，与白名单存在相同IP时，优先于白名单 */
////        servletRegistrationBean.addInitParameter("deny","192.0.0.1");
////        /** 用户名 */
////        servletRegistrationBean.addInitParameter("loginUsername","jikefriend");
////        /** 密码 */
////        servletRegistrationBean.addInitParameter("loginPassword","jikefriend");
//            /** 禁用页面上的“Reset All”功能 */
//            servletRegistrationBean.addInitParameter("resetEnable", "false");
//            servletRegistrationBean.addInitParameter("allow", "");
//            servletRegistrationBean.setServlet(new StatViewServlet());
//            servletRegistrationBean.addUrlMappings("/druid/*");
//            return servletRegistrationBean;
//        }
//
//        @Bean
//        public FilterRegistrationBean filterRegistrationBean() {
//            FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//            filterRegistrationBean.setFilter(new WebStatFilter());
//            filterRegistrationBean.addUrlPatterns("/*");
//            filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*,*.html");
////            DelegatingFilterProxy proxy = new DelegatingFilterProxy();
////            proxy.setTargetFilterLifecycle(true);
////            proxy.setTargetBeanName("springFilter");
////            filterRegistrationBean.setFilter(proxy);
//            return filterRegistrationBean;
//        }
//    }

}
