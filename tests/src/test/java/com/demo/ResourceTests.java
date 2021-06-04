package com.demo;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.Reader;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author ZhangShaowei on 2021/6/4 9:07
 */
public class ResourceTests {


    @SneakyThrows
    public static void main(String[] args) {
        String path = "G:\\MyGithub\\spring\\tests\\src\\test\\java\\com\\demo\\ResourceTests.java";
        FileSystemResource fileSystemResource = new FileSystemResource(Paths.get(path));
        EncodedResource encodedResource = new EncodedResource(fileSystemResource, UTF_8);
        Reader reader = encodedResource.getReader();
        String java = IOUtils.toString(reader);
        System.out.println(java);

    }

}
