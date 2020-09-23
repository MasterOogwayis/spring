package com.zsw.pojo.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2020/9/23 15:12
 */
@Data
@Document(indexName = "customer")
public class CustomerDto implements Serializable {
    private static final long serialVersionUID = -3854087827601891673L;

    @Id
    private Long id;

    private String name;

    private Integer age;

    private String address;

}
