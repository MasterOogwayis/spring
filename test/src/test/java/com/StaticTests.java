package com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.DatagramPacket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author ZhangShaowei on 2019/10/9 10:03
 **/
@Slf4j
public class StaticTests {

    public static final Gson GSON = new GsonBuilder().serializeNulls().create();


    @SneakyThrows
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.parse("2019-11-21 00:00:00"));


    }

    @AllArgsConstructor
    static class Dto {
        private Long id;
        private String name;
    }

}
