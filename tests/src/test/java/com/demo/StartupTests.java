package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author ZhangShaowei on 2021/5/24 14:49
 */
@Slf4j
public class StartupTests {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });
        String url = "http://localhost:18080/actuator/startup";

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, null, String.class);

        log.info("body={}", responseEntity.getBody());


    }

}
