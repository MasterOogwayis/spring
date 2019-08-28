package com.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
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

//        String path = "G:\\workplace\\platform-soa-config-files\\future";
//
//        Stream.of(Paths.get(path).toFile().list()).forEach(name -> {
//            if (name.endsWith("-test.yml")) {
//                String newName = name.replace("-test.yml", "-future.yml");
//                Paths.get(path, name).toFile().renameTo(Paths.get(path, newName).toFile());
//            }
//        });

//        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//
//        List<String> list = new ArrayList<>();
//        list.add("easylines-weixin");
//        list.add("M17403895");
//        list.add("7DEBA6345005");
//        list.add("1CB94F1AFE04");
//        list.add("B12B38317954");
//        list.add("7C0904F2F9E4");
//        list.add("M32457016");
//        list.add("B12B38317954");
//        list.add("easylines-distribution-customer");
//        list.add("K3478Y2456787");
//        list.add("B57N47832473");
//
//        Map<String, String> collect = list.stream().distinct().collect(Collectors.toMap(Function.identity(), passwordEncoder::encode));
//
//        collect.forEach((key, value) -> {
//            log.info("password={}, encrypt={}", key, value);
//        });


        SortedSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }

        SortedSet<Integer> headSet = set.headSet(23);
        System.out.println(headSet);

        headSet.clear();
        System.err.println(set);


    }

    private static byte[] get() {
        return null;
    }


}
