package com.zsw;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author ZhangShaowei on 2021/8/25 11:11
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class SimpleWebMvcTests {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    public void before() {
        log.info("before ... port = {}", port);
    }

    @Autowired
    private WebTestClient client;

    /**
     *
     */
    @Test
    public void test() {
        client
                .get().uri("/config")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.profiles").isEqualTo("dev")
                .jsonPath("$.db").isEqualTo("redis");
//                .expectBody(String.class).isEqualTo("Hello World");
    }

    @AfterEach
    public void after() {
        log.info("after ...");
    }

}
