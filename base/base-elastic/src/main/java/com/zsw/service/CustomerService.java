package com.zsw.service;

import com.zsw.pojo.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author ZhangShaowei on 2020/9/23 15:14
 */
@Service
public class CustomerService {

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

//    @Autowired
//    private ElasticsearchOperations elasticsearchOperations;


    public CustomerDto save(CustomerDto customerDto) {
//        IndexQuery indexQuery = new IndexQueryBuilder()
//                .withId(customerDto.getId().toString())
//                .withObject(customerDto)
//                .build();
        return this.restTemplate.save(customerDto);
    }


    public List<CustomerDto> search(String name) {
        return Collections.emptyList();
    }

}
