package com.zsw;

import com.zaxxer.hikari.HikariDataSource;
import io.seata.spring.annotation.datasource.SeataAutoDataSourceProxyAdvice;
import io.seata.spring.boot.autoconfigure.SeataDataSourceAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * @author ZhangShaowei on 2021/6/7 13:22
 */
@EnableDiscoveryClient
@SpringBootApplication
public class StorageApp {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(StorageApp.class, args);
    }

    /**
     * Auto Proxy 的方式直接使用了 {@link SeataAutoDataSourceProxyAdvice} 代理了 DataSource
     * 不需要再手动创建 DataSourceProxy
     * <p>
     * FIXME 这是框架的坑
     * 下面的自动代理 DataSource 并没有做好，java bean 配置方式本身就存在顺序问题，
     * 默认是读取不到 DataSourceAutoConfiguration 配置的 DataSource
     * 所以需要在这里提前标注
     *
     * @param properties
     * @return
     * @see SeataDataSourceAutoConfiguration
     */
    @Bean
    public DataSource dataSource(DataSourceProperties properties) {
        HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }

//    @Bean
//    public JdbcTemplate jdbcTemplate(
//            DataSource dataSource, JdbcProperties properties, SeataProperties seataProperties) {
////        SeataDataSourceProxy seataDataSourceProxy
////                = DataSourceProxyHolder.get().putDataSource(
////                dataSource, BranchType.get(seataProperties.getDataSourceProxyMode()));
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        JdbcProperties.Template template = properties.getTemplate();
//        jdbcTemplate.setFetchSize(template.getFetchSize());
//        jdbcTemplate.setMaxRows(template.getMaxRows());
//        if (template.getQueryTimeout() != null) {
//            jdbcTemplate.setQueryTimeout((int) template.getQueryTimeout().getSeconds());
//        }
//        return jdbcTemplate;
//    }


}
