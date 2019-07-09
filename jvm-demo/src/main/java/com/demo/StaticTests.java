package com.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
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
        String crt = "/data/XSHb2c.e.0200.crt";

        byte[] bytes = readCert(crt);
        byte[] bytes1 = readCert1(crt);
        System.out.println(bytes);
        System.out.println(bytes1);

    }


    public static byte[] readCert(String filePath) throws Exception {
        return Files.readAllBytes(Paths.get(filePath));
    }

    public static byte[] readCert1(String filePath) throws Exception {
        if (filePath != null && !"".equals(filePath)) {
            try {
                FileInputStream fs = new FileInputStream(filePath);
                byte[] bsc = new byte[fs.available()];
                fs.read(bsc);
                fs.close();
                return bsc;
            } catch (IOException var3) {
                throw var3;
            }
        } else {
            return null;
        }
    }

}
