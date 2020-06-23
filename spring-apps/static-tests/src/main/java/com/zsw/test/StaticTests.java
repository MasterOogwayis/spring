package com.zsw.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2020/4/30 16:43
 */
public class StaticTests {

    private static final ObjectMapper OM = new ObjectMapper();

    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(i);
        }
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Ci {
        @JsonProperty("T_name")
        private String name;

        private String value;
    }

}
