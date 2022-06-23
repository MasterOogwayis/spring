package com.tests;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Shaowei Zhang on 2022/4/6 22:40
 */
@Slf4j
public class JsonReadTests {

    @SneakyThrows
    public static void main(String[] args) {
        ClassPathResource temp = new ClassPathResource("/temp");
        File[] files = temp.getFile()
                .listFiles(file -> "json".equals(StringUtils.getFilenameExtension(file.getName())));

        for (File file : files) {
            byte[] bytes = Files.readAllBytes(file.toPath());
            String json = new String(bytes, UTF_8);
            JSONObject jsonObject = JSONObject.parseObject(json);
            System.err.println("- - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println(jsonObject);
        }

    }


    protected JSONObject getCssMapping(String filename) {
        ClassPathResource classPathResource = new ClassPathResource("/temp/" + filename);
        return null;
    }

}
