package com.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

/**
 * @author ZhangShaowei on 2019/2/27 14:15
 **/
@Slf4j
public class StaticTests {


    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    @SneakyThrows
    public static void main(String[] args) {

        String dir = "G:\\workplace\\platform-soa-config-files\\new";
//        Files.getFileStore(Paths.get(path)).
        Files.list(Paths.get(dir)).forEach(path -> {
            String s = path.getFileName().toString();
            if (s.endsWith("-dev.yml")) {
                s = s.replace("-dev.yml", "-new.yml");
                System.out.println(s);

                path.toFile().renameTo(Paths.get(dir, s).toFile());

            }


        });

    }


}
