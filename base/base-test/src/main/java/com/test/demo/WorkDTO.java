package com.test.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2019/6/21 11:25
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkDTO implements Serializable {
    private static final long serialVersionUID = 3511911710778559024L;

    private String work;

}
