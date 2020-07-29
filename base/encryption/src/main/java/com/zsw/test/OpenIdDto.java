package com.zsw.test;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2018/9/11 18:00
 */
public class OpenIdDto implements Serializable {

    private String token;


    /**  */
    public String getToken() {
        return token;
    }

    /**  */
    public void setToken(String token) {
        this.token = token;
    }


}
