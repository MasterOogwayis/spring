package com;

import lombok.Data;

/**
 * @author ZhangShaowei on 2019/2/19 13:41
 **/
@Data
public class Params {

    private Params() {
        this.name = name;
    }

    private String name;

}
