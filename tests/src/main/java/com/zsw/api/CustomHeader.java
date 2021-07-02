package com.zsw.api;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ZhangShaowei on 2021/7/2 17:02
 */
@Getter
@Setter
public class CustomHeader implements HeaderInjection {

    private String name;

    private String address;

}
