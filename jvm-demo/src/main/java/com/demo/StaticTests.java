package com.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2019/2/27 14:15
 **/
@Slf4j
public class StaticTests {


    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    @SneakyThrows
    public static void main(String[] args) {

        String path = "G:\\workplace\\platform-soa-config-files\\future";

        Stream.of(Paths.get(path).toFile().list()).forEach(name -> {
            if (name.endsWith("-test.yml")) {
                String newName = name.replace("-test.yml", "-future.yml");
                Paths.get(path, name).toFile().renameTo(Paths.get(path, newName).toFile());
            }
        });



    }


}
