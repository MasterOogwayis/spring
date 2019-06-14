package com;

import com.zsw.http.backup.okhttp.OkHttpRequest;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ZhangShaowei on 2019/5/27 16:34
 **/
public class TestHttp {


    public static void main(String[] args) {

        OkHttpRequest client = new OkHttpRequest();


    }


    @Data
    class IdCardAuthResponse {
        private static final String SUCCESS = "200000";

        /**
         * 01-认证一致(收费) 02-认证不一致(收费) 03-认证不确定（收费）   04-认证失败(不收费)
         */
        private static final String AUTH_SUCCESS = "01";

        private String code;
        private String message;
        private Map<String, String> data;

        public boolean isSuccess() {
            return SUCCESS.equals(this.code);
        }

        public boolean isAuthSuccess() {
            return this.isSuccess() && AUTH_SUCCESS.equalsIgnoreCase(this.data.get("result"));
        }

    }

}
