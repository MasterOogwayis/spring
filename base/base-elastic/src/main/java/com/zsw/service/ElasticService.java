package com.zsw.service;

import org.elasticsearch.action.index.IndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2020/9/23 12:11
 */
@Service
public class ElasticService {

    @Autowired
    private ElasticsearchRestTemplate template;


    /**
     * @param world
     * @return
     */
    public Object search(String world) {
        StringQuery stringQuery = new StringQuery(world, PageRequest.of(1, 15), Sort.by(Sort.Direction.ASC, "age"));
        SearchHits<String> search = this.template.search(stringQuery, String.class);
        return search;
    }


}
