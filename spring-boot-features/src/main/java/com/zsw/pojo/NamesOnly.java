package com.zsw.pojo;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author ZhangShaowei on 2021/8/31 10:34
 */
public interface NamesOnly {

    String getFirstName();

    String getLastName();

    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();

}
