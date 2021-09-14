package com.zsw.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadataProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @author ZhangShaowei on 2021/9/14 10:48
 */
@RequestMapping("datasource")
@RestController
public class DataSourceEndpoint {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DataSourcePoolMetadataProvider metadataProvider;

    @GetMapping
    public Object get() {
        return metadataProvider.getDataSourcePoolMetadata(dataSource);
    }


}
