package com.zsw.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

/**
 * @author ZhangShaowei on 2020/9/23 14:58
 * @see org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration
 */
@Configuration
public class ElasticRestClientConfig {

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration configuration = ClientConfiguration.builder()
                .connectedTo("192.168.1.13:9200").build();
        return RestClients.create(configuration).rest();
    }
}
