package com.zsw.data.orm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * 由于外面要设值，这里吧解析后的 map 一并返回
 *
 * @author Shaowei Zhang on 2019/4/22 21:35
 **/
@Data
@AllArgsConstructor
public class PropertiesHandler {

    /**
     *
     */
    private String sql;

    /**
     * T -> map
     */
    private Map<String, Object> properties;

}
