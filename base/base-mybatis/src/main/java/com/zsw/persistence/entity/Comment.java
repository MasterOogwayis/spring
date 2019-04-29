package com.zsw.persistence.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ZhangShaowei on 2019/4/28 11:45
 **/
@Data
public class Comment implements Serializable {

    private String author;

    private String context;

    private Date createDate;

}
