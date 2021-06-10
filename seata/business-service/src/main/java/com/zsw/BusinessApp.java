package com.zsw;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ZhangShaowei on 2021/6/7 10:09
 */
@EnableAutoDataSourceProxy
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class BusinessApp {


    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(BusinessApp.class, args);
    }

//
//    @Primary
//    @Bean
//    public DataSource dataSource(DataSourceProperties properties) {
//        HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//        if (StringUtils.hasText(properties.getName())) {
//            dataSource.setPoolName(properties.getName());
//        }
//        return new DataSourceProxy(dataSource);
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource, JdbcProperties properties) {
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
