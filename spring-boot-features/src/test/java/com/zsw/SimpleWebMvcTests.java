package com.zsw;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author ZhangShaowei on 2021/8/25 11:11
 */
@Slf4j
@SpringBootTest
@AutoConfigureWebClient
public class SimpleWebMvcTests {

    @Before
    public void before() {
        log.info("before ...");
    }

    /**
     * FIXME 错误
     *
     * @param client
     */
    @Test
    public void test(@Autowired WebTestClient client) {
        client
                .get().uri("/config")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.profiles").isEqualTo("dev")
                .jsonPath("$.db").isEqualTo("redis");
//                .expectBody(String.class).isEqualTo("Hello World");
    }

    @After
    public void after() {
        log.info("after ...");
    }

}
