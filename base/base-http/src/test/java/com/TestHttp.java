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

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("appId", "EpfLvpuS");
//        params.put("appKey", "lxert2Tf");
        params.put("name", "张少伟");
        params.put("idNum", "510182198907297030");

        String sign = "appIdEpfLvpuSidNum510182198907297030name张少伟";
        sign = SHA1.hmacSHA1ToBase64String(sign, "lxert2Tf");

        params.put("sign", sign);
        IdCardAuthResponse response = client.formSubmit(
//                "https://api.253.com/open/idcard/id-card-auth",
                "https://api.253.com/open/idcard/id-card-auth/vs",
                params,
                IdCardAuthResponse.class
        );
        System.out.println(response);

        System.err.println(response.isAuthSuccess());

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
